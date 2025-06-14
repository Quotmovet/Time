package com.example.time.domain.interactor.alarmscreen.impl

import com.example.time.domain.interactor.alarmscreen.AlarmScreenInteractor
import com.example.time.domain.model.alarmscreen.AlarmModel
import com.example.time.domain.repositories.alarmscreen.AlarmScreenRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AlarmScreenInteractorImpl @Inject constructor (
    private val alarmScreenRepository: AlarmScreenRepository
): AlarmScreenInteractor {

    override suspend fun insertAlarm(alarm: AlarmModel) {
        alarmScreenRepository.insertAlarm(alarm)
    }

    override fun getAllAlarms(): Flow<List<AlarmModel>> {
        return alarmScreenRepository.getAllAlarms()
    }

    override suspend fun deleteAlarmById(id: Int) {
        alarmScreenRepository.deleteAlarmById(id)
    }
}