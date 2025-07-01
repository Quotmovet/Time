package com.example.time.presentation.viewmodel.alarmscreen

import android.net.Uri
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
) : ViewModel() {

    private val _alarmState = MutableStateFlow<List<AlarmModel>>(emptyList())
    val alarmState: StateFlow<List<AlarmModel>> = _alarmState.asStateFlow()

    init {
        observeAlarms()
    }

    fun insertAlarm(alarm: AlarmModel) {
        viewModelScope.launch {
            alarmScreenInteractor.insertAlarm(alarm)
            if (alarm.isActivated) alarmScreenScheduler.schedule(alarm)
            else alarmScreenScheduler.cancel(alarm)
        }
    }

    fun deleteAlarm(alarm: AlarmModel) {
        viewModelScope.launch {
            alarmScreenInteractor.deleteAlarmById(alarm.id)
            alarmScreenScheduler.cancel(alarm)
        }
    }

    fun updateAlarmActivation(alarm: AlarmModel, isActivated: Boolean) {
        insertAlarm(alarm.copy(isActivated = isActivated))
    }

    fun updateAlarmDays(alarm: AlarmModel, day: Int) {
        val daysSet = alarm.days.split(",")
            .mapNotNull { it.trim().toIntOrNull() }
            .toMutableSet()

        if (daysSet.contains(day)) daysSet.remove(day) else daysSet.add(day)
        val updated = daysSet.sorted().joinToString(",")
        insertAlarm(alarm.copy(days = updated))
    }

    fun updateAlarmName(alarm: AlarmModel, newName: String) {
        insertAlarm(alarm.copy(name = newName))
    }

    fun updateAlarmVibration(alarm: AlarmModel, isVibration: Boolean) {
        insertAlarm(alarm.copy(isVibration = isVibration))
    }

    fun updateAlarmSound(alarm: AlarmModel, uri: Uri?) {
        uri?.toString()?.let { insertAlarm(alarm.copy(sound = it)) }
    }

    fun createAlarm(hour: Int, minute: Int) {
        insertAlarm(
            AlarmModel(
                id = 0,
                hour = hour,
                minute = minute,
                name = "",
                isActivated = true,
                isVibration = true,
                days = "",
                sound = ""
            )
        )
    }

    private fun observeAlarms() {
        viewModelScope.launch {
            alarmScreenInteractor.getAllAlarms()
                .collect { _alarmState.value = it }
        }
    }
}