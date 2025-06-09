package com.example.time.presentation.viewmodel.timescreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.time.domain.usecase.timescreen.GetCurrentDateUseCase
import com.example.time.domain.usecase.timescreen.GetCurrentTimeUseCase
import com.example.time.presentation.common.util.Constants.ONE_MINUTE
import com.example.time.presentation.common.util.Constants.ONE_SECOND
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.example.time.data.db.timescreen.converter.toModelFromEntity
import com.example.time.domain.interactor.timescreen.SelectedTimeZoneInteractor
import com.example.time.domain.model.timescreen.TimeDataModel
import com.example.time.presentation.common.util.formater.formatTime
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class TimeScreenViewModel @Inject constructor (
    private val getCurrentTimeUseCase: GetCurrentTimeUseCase,
    private val getCurrentDateUseCase: GetCurrentDateUseCase,
    private val selectedTimeZoneInteractor: SelectedTimeZoneInteractor,
) : ViewModel() {

    private val _currentTime = mutableStateOf(getCurrentTimeUseCase())
    val currentTime: State<String> = _currentTime

    private val _currentDate = mutableStateOf(getCurrentDateUseCase())
    val currentDate: State<String> = _currentDate

    private val _selectedTimeState = MutableStateFlow<List<TimeDataModel>>(emptyList())
    val selectedTimeState: StateFlow<List<TimeDataModel>> = _selectedTimeState.asStateFlow()

    init {
        getSelectedTimezone()
        startTimeUpdates()
        startDateUpdates()
        startSelectedTimeUpdateLoop()
    }

    fun getSelectedTimezone() {
        viewModelScope.launch {
            selectedTimeZoneInteractor.getSelectedTimeData().collect { timezone ->
                val selected = timezone.map { it.toModelFromEntity() }
                _selectedTimeState.value = selected
            }
        }
    }

    fun deleteSelectedTimezone(timezone: String) {
        viewModelScope.launch {
            selectedTimeZoneInteractor.deleteSelectedTimezone(timezone)
        }
    }

    private fun startSelectedTimeUpdateLoop() {
        viewModelScope.launch {
            while (true) {
                delay(ONE_SECOND)
                _selectedTimeState.value = _selectedTimeState.value.map { model ->
                    model.copy(time = formatTime(model.offset))
                }
            }
        }
    }

    private fun startTimeUpdates() {
        viewModelScope.launch {
            while (true) {
                delay(ONE_SECOND)
                _currentTime.value = getCurrentTimeUseCase()
            }
        }
    }

    private fun startDateUpdates() {
        viewModelScope.launch {
            while (true) {
                delay(ONE_MINUTE)
                _currentDate.value = getCurrentDateUseCase()
            }
        }
    }
}