package com.example.time.presentation.viewmodel.searchscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.time.data.db.timescreen.converter.toEntityFromModel
import com.example.time.data.db.timescreen.converter.toModelFromEntity
import com.example.time.domain.interactor.timescreen.SelectedTimeZoneInteractor
import com.example.time.domain.interactor.timescreen.TimeZoneDataInteractor
import com.example.time.domain.model.timescreen.TimeDataModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelForAPI
    @Inject
    constructor(
        private val timeZoneDataInteractor: TimeZoneDataInteractor,
        private val selectedTimeZoneInteractor: SelectedTimeZoneInteractor,
    ) : ViewModel() {
        companion object {
            private const val SEARCH_DELAY = 1000L
        }

        private val _searchState =
            MutableStateFlow<SearchScreenState>(
                SearchScreenState(
                    timeZone = emptyList(),
                    isLoading = false,
                    isFailed = null,
                    isEmpty = false,
                ),
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
                _searchState.value =
                    SearchScreenState(
                        timeZone = emptyList(),
                        isLoading = false,
                        isFailed = null,
                        isEmpty = false,
                    )
                return
            }

            searchJob?.cancel()
            searchJob =
                viewModelScope.launch {
                    delay(SEARCH_DELAY)
                    searchRequest(changedText)
                }
        }

        // Request
        fun searchRequest(newSearchText: String) {
            _searchState.value = SearchScreenState(emptyList(), isLoading = true, isFailed = null, isEmpty = false)

            viewModelScope.launch {
                timeZoneDataInteractor.getTimeZone(newSearchText).collect { response ->
                    if (response.isFailed != null) {
                        _searchState.value =
                            SearchScreenState(
                                timeZone = emptyList(),
                                isLoading = false,
                                isFailed = response.isFailed,
                                isEmpty = false,
                            )
                    } else {
                        setTimeState(response.data ?: emptyList())
                    }
                }
            }
        }

        fun insertSelectedTimeData(data: TimeDataModel) {
            viewModelScope.launch(Dispatchers.IO) {
                selectedTimeZoneInteractor.insertSelectedTimeData(data.toEntityFromModel())
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
            getSelected()
        }

        private fun updateSelectedTimeZone(data: List<TimeDataModel>): List<TimeDataModel> {
            val currentTimeZones = _searchState.value.timeZone
            return currentTimeZones.map { item ->
                val isSelected = data.any { it.timeZone == item.timeZone }
                item.copy(isSelected = isSelected)
            }
        }

        private fun postTimeState() {
            _searchState.value =
                SearchScreenState(
                    timeZone = emptyList(),
                    isLoading = false,
                    isFailed = null,
                    isEmpty = false,
                )
        }

        private fun setTimeState(timeZone: List<TimeDataModel>) {
            _searchState.value =
                SearchScreenState(
                    timeZone = timeZone,
                    isLoading = false,
                    isFailed = null,
                    isEmpty = timeZone.isEmpty(),
                )
        }
    }
