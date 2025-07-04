package com.example.time.domain.repositories.alarmscreen

import com.example.time.domain.model.alarmscreen.AlarmModel
import kotlinx.coroutines.flow.Flow

interface AlarmScreenRepository {
    suspend fun insertAlarm(alarm: AlarmModel)

    fun getAllAlarms(): Flow<List<AlarmModel>>

    suspend fun getAlarmById(id: Int): AlarmModel

    suspend fun deleteAlarmById(id: Int)
}
