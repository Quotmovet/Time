package com.example.time.presentation.viewmodel.alarmscreen

import android.app.Application
import android.content.Intent
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.time.data.service.alarmscreen.AlarmService
import com.example.time.domain.interactor.alarmscreen.AlarmScreenInteractor
import com.example.time.domain.model.alarmscreen.AlarmModel
import com.example.time.domain.scheduler.alarmscreen.AlarmScheduler
import com.example.time.domain.usecase.timescreen.GetCurrentDateUseCase
import com.example.time.presentation.common.util.formater.toCalendarDay
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class AlarmViewModelDuringAlarm @Inject constructor(
    application: Application,
    getCurrentDateUseCase: GetCurrentDateUseCase,
    private val alarmScreenInteractor: AlarmScreenInteractor,
    private val alarmScheduler: AlarmScheduler
): AndroidViewModel(application) {

    private val _currentDate = mutableStateOf(getCurrentDateUseCase())
    val currentDate: State<String> = _currentDate

    private val _alarm = mutableStateOf<AlarmModel?>(null)
    val alarm: State<AlarmModel?> = _alarm

    fun getAlarmById(alarmId: Int) {
        viewModelScope.launch {
            _alarm.value = alarmScreenInteractor.getAlarmById(alarmId)
        }
    }

    fun dismissToday() {
        _alarm.value?.let { alarm ->
            viewModelScope.launch {
                val today = LocalDate.now().dayOfWeek.toCalendarDay()
                alarmScheduler.cancelForDay(alarm, today)
                stopAlarmService()
            }
        }
    }

    fun snoozeToday() {
        _alarm.value?.let { alarm ->
            viewModelScope.launch {
                val newTime = Calendar.getInstance().apply {
                    add(Calendar.MINUTE, 5)
                }
                alarmScheduler.schedulePostponeOnce(alarm, newTime.timeInMillis)
                stopAlarmService()
            }
        }
    }

    private fun stopAlarmService() {
        val context = getApplication<Application>().applicationContext
        val stopIntent = Intent(context, AlarmService::class.java)
        context.stopService(stopIntent)
    }

}