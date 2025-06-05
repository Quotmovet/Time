package com.example.time.data.repositories.timescreen

import com.example.time.data.db.AppDatabase
import com.example.time.data.db.timescreen.entity.TimeDataEntity
import com.example.time.domain.repositories.timescreen.SelectedTimeZoneRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SelectedTimeZoneRepositoryImpl @Inject constructor (
    private val appDatabase: AppDatabase
): SelectedTimeZoneRepository {

    override suspend fun insertSelectedTimeData(data: TimeDataEntity) {
        withContext(Dispatchers.IO) {
            appDatabase.getTimeDataDao().insertSelectedTimeData(data)
        }
    }

    override fun getSelectedTimeData(): Flow<List<TimeDataEntity>> {
        return appDatabase.getTimeDataDao().getSelectedTimeData()
    }

    override suspend fun deleteSelectedTimezone(timezone: String) {
        withContext(Dispatchers.IO) {
            appDatabase.getTimeDataDao().deleteSelectedTimezone(timezone)
        }
    }

}