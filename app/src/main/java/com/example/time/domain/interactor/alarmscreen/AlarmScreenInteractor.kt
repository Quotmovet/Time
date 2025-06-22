package com.example.time.domain.interactor.alarmscreen

import com.example.time.domain.model.alarmscreen.AlarmModel
import kotlinx.coroutines.flow.Flow

interface AlarmScreenInteractor {
    suspend fun insertAlarm(alarm: AlarmModel)
    fun getAllAlarms(): Flow<List<AlarmModel>>
    suspend fun getAlarmById(id: Int): AlarmModel
    suspend fun deleteAlarmById(id: Int)
}