package com.example.time.presentation.viewmodel.alarmscreen

import android.app.Application
import android.content.Intent
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.time.domain.interactor.alarmscreen.AlarmScreenInteractor
import com.example.time.domain.model.alarmscreen.AlarmModel
import com.example.time.domain.scheduler.alarmscreen.AlarmScreenScheduler
import com.example.time.domain.usecase.timescreen.GetCurrentDateUseCase
import com.example.time.domain.usecase.timescreen.GetCurrentTimeUseCase
import com.example.time.presentation.common.util.formater.toCalendarDay
import com.example.time.presentation.service.alarmscreen.AlarmService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class AlarmViewModelDuringAlarm @Inject constructor(
    application: Application,
    getCurrentDateUseCase: GetCurrentDateUseCase,
    getCurrentTimeUseCase: GetCurrentTimeUseCase,
    private val alarmScreenInteractor: AlarmScreenInteractor,
    private val alarmScreenScheduler: AlarmScreenScheduler
): AndroidViewModel(application) {

    private val _currentDate = mutableStateOf(getCurrentDateUseCase())
    val currentDate: State<String> = _currentDate

    private val _currentTime = mutableStateOf(getCurrentTimeUseCase())
    val currentTime: State<String> = _currentTime

    private val _alarm = mutableStateOf<AlarmModel?>(null)
    val alarm: State<AlarmModel?> = _alarm

    private val _uiEvents = MutableSharedFlow<AlarmUiEvent>()
    val uiEvents: SharedFlow<AlarmUiEvent> = _uiEvents

    fun getAlarmById(alarmId: Int) {
        viewModelScope.launch {
            _alarm.value = alarmScreenInteractor.getAlarmById(alarmId)
        }
    }

    fun dismissToday() {
        _alarm.value?.let { alarm ->
            viewModelScope.launch {
                val today = LocalDate.now().dayOfWeek.toCalendarDay()
                alarmScreenScheduler.cancelForDay(alarm, today)
                stopAlarmService()
                _uiEvents.emit(AlarmUiEvent.Finish)
            }
        }
    }

    fun snoozeToday() {
        _alarm.value?.let { alarm ->
            viewModelScope.launch {
                val newTime = Calendar.getInstance().apply {
                    add(Calendar.MINUTE, 5)
                }
                alarmScreenScheduler.schedulePostponeOnce(alarm, newTime.timeInMillis)
                stopAlarmService()
                _uiEvents.emit(AlarmUiEvent.Finish)
            }
        }
    }

    private fun stopAlarmService() {
        val context = getApplication<Application>().applicationContext
        val stopIntent = Intent(context, AlarmService::class.java)
        context.stopService(stopIntent)
    }

}