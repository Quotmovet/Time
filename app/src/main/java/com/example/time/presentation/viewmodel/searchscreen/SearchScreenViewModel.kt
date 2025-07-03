package com.example.time.presentation.viewmodel.searchscreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.time.data.db.timescreen.converter.toEntityFromModel
import com.example.time.data.db.timescreen.converter.toModelFromEntity
import com.example.time.domain.interactor.timescreen.DataSourceTimeInteractor
import com.example.time.domain.interactor.timescreen.SelectedTimeZoneInteractor
import com.example.time.domain.model.timescreen.TimeDataModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException

@HiltViewModel
class SearchScreenViewModel @Inject constructor(
    private val dataSourceTimeInteractor: DataSourceTimeInteractor,
    private val selectedTimeZoneInteractor: SelectedTimeZoneInteractor
): ViewModel() {

    companion object {
        private const val SEARCH_DELAY = 1000L
    }

    private val _searchState = MutableStateFlow<SearchScreenState>(
        SearchScreenState(
            timeZone = emptyList(),
            isLoading = false,
            isFailed = null,
            isEmpty = false
        )
    )
    val searchState: StateFlow<SearchScreenState> = _searchState.asStateFlow()

    private val _selectedTimeState = MutableStateFlow<List<TimeDataModel>>(emptyList())
    val selectedTimeState: StateFlow<List<TimeDataModel>> = _selectedTimeState.asStateFlow()

    init {
        postTimeState()
    }

    private var searchJob: Job? = null

    // Debouncer
    fun searchDebouncer(changedText: String) {
        if (changedText.isEmpty()) {
            _searchState.value = SearchScreenState(
                timeZone = emptyList(),
                isLoading = false,
                isFailed = null,
                isEmpty = false
            )
            return
        }

        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(SEARCH_DELAY)
            getTimeDataFromDataSource(changedText)
        }
    }

    // Search
    fun getTimeDataFromDataSource(newSearchText: String) {
        _searchState.value = SearchScreenState(emptyList(), isLoading = true, isFailed = null, isEmpty = false)

        viewModelScope.launch {
            try {
                dataSourceTimeInteractor.getDataSourceTime(newSearchText).collect { response ->
                    setTimeState(response)
                }
            } catch (e: IOException) {
                _searchState.value = _searchState.value.copy(
                    isLoading = false,
                    isFailed = true,
                    isEmpty = false
                )
                Log.e("SearchViewModel", "IO Exception while fetching time data", e)
            } catch (e: TimeoutCancellationException) {
                _searchState.value = _searchState.value.copy(
                    isLoading = false,
                    isFailed = true,
                    isEmpty = false
                )
                Log.e("SearchViewModel", "Timeout while fetching time data", e)
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                _searchState.value = _searchState.value.copy(
                    isLoading = false,
                    isFailed = true,
                    isEmpty = false
                )
                Log.e("SearchViewModel", "Unexpected error while fetching time data", e)
            }
        }
    }

    fun insertSelectedTimeData(data: TimeDataModel) {
        viewModelScope.launch(Dispatchers.IO) {
            selectedTimeZoneInteractor.insertSelectedTimeData(data.toEntityFromModel())
            delay(100)
            getSelected()
        }
    }

    fun getSelected() {
        viewModelScope.launch(Dispatchers.IO) {
            selectedTimeZoneInteractor.getSelectedTimeData().collect { entities ->
                val selected = entities.map { it.toModelFromEntity() }

                _selectedTimeState.value = selected

                val updatedTimeZones = updateSelectedTimeZone(selected)

                _searchState.value = _searchState.value.copy(timeZone = updatedTimeZones)
            }
        }
    }

    fun deleteSelectedTimezone(timezone: String) {
        viewModelScope.launch(Dispatchers.IO) {
            selectedTimeZoneInteractor.deleteSelectedTimezone(timezone)
        }
    }

    private fun updateSelectedTimeZone(data: List<TimeDataModel>): List<TimeDataModel> {
        val currentTimeZones = _searchState.value.timeZone
        return currentTimeZones.map { item ->
            val isSelected = data.any { it.timeZone == item.timeZone }
            item.copy(isSelected = isSelected)
        }
    }

    private fun postTimeState(){
        _searchState.value = SearchScreenState(
            timeZone = emptyList(),
            isLoading = false,
            isFailed = null,
            isEmpty = false
        )
    }

    private fun setTimeState(timeZone: List<TimeDataModel>) {
        println("Filtered: ${timeZone.size}")
        _searchState.value = SearchScreenState(
            timeZone = timeZone,
            isLoading = false,
            isFailed = null,
            isEmpty = timeZone.isEmpty()
        )
    }
}