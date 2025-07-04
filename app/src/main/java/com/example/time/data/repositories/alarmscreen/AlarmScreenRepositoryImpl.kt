package com.example.time.data.repositories.alarmscreen

import com.example.time.data.db.AppDatabase
import com.example.time.data.db.alarmscreen.converter.toEntityFromModel
import com.example.time.data.db.alarmscreen.converter.toModelFromEntity
import com.example.time.domain.model.alarmscreen.AlarmModel
import com.example.time.domain.repositories.alarmscreen.AlarmScreenRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AlarmScreenRepositoryImpl
    @Inject
    constructor(
        private val appDatabase: AppDatabase,
    ) : AlarmScreenRepository {
        override suspend fun insertAlarm(alarm: AlarmModel) {
            withContext(Dispatchers.IO) {
                val alarmEntity = alarm.toEntityFromModel()
                appDatabase.getAlarmDao().insertAlarm(alarmEntity)
            }
        }

        override fun getAllAlarms(): Flow<List<AlarmModel>> {
            return appDatabase.getAlarmDao()
                .getAllAlarms()
                .map { alarmEntities ->
                    alarmEntities.map { it.toModelFromEntity() }
                }
        }

        override suspend fun getAlarmById(id: Int): AlarmModel {
            return withContext(Dispatchers.IO) {
                val alarmEntity = appDatabase.getAlarmDao().getAlarmById(id)
                alarmEntity.toModelFromEntity()
            }
        }

        override suspend fun deleteAlarmById(id: Int) {
            withContext(Dispatchers.IO) {
                appDatabase.getAlarmDao().deleteAlarmById(id)
            }
        }
    }
