package com.example.time.data.repositories.stopwatchscreen

import com.example.time.data.db.AppDatabase
import com.example.time.data.db.stopwatchscreen.converter.toEntityFromModel
import com.example.time.data.db.stopwatchscreen.converter.toModelFromEntity
import com.example.time.domain.model.stopwatchscreen.StopwatchModel
import com.example.time.domain.repositories.stopwatchscreen.DBStopwatchScreenRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DBStopwatchScreenRepositoryImpl
    @Inject
    constructor(
        private val appDatabase: AppDatabase
    ) : DBStopwatchScreenRepository {
        override suspend fun insertStopwatch(model: StopwatchModel) {
            withContext(Dispatchers.IO) {
                val stopwatchEntity = model.toEntityFromModel()
                appDatabase.getStopwatchDao().insertStopwatch(stopwatchEntity)
            }
        }

        override suspend fun getAllStopwatches(): List<StopwatchModel> {
            return withContext(Dispatchers.IO) {
                appDatabase.getStopwatchDao().getAllStopwatches().map { it.toModelFromEntity() }
            }
        }

        override suspend fun resetStopwatches() {
            withContext(Dispatchers.IO) {
                appDatabase.getStopwatchDao().resetStopwatches()
            }
        }
    }