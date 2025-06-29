package com.example.time.presentation.viewmodel.alarmscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.time.domain.interactor.alarmscreen.AlarmScreenInteractor
import com.example.time.domain.model.alarmscreen.AlarmModel
import com.example.time.domain.scheduler.alarmscreen.AlarmScreenScheduler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlarmScreenViewModel @Inject constructor(
    private val alarmScreenInteractor: AlarmScreenInteractor,
    private val alarmScreenScheduler: AlarmScreenScheduler
): ViewModel() {

    private val _alarmState = MutableStateFlow<List<AlarmModel>>(emptyList())
    val alarmState: StateFlow<List<AlarmModel>> = _alarmState.asStateFlow()

    init {
        observeAlarms()
    }

    fun insertAlarm(alarm: AlarmModel) {
        viewModelScope.launch {
            alarmScreenInteractor.insertAlarm(alarm)

            if (alarm.isActivated) {
                alarmScreenScheduler.schedule(alarm)
            } else {
                alarmScreenScheduler.cancel(alarm)
            }
        }
    }

    fun deleteAlarm(alarm: AlarmModel) {
        val alarmId = alarm.id
        viewModelScope.launch {
            alarmScreenInteractor.deleteAlarmById(alarmId)
            alarmScreenScheduler.cancel(alarm)
        }
    }

    private fun observeAlarms() {
        viewModelScope.launch {
            alarmScreenInteractor.getAllAlarms()
                .collect { alarms ->
                    _alarmState.value = alarms
                }
        }
    }
}