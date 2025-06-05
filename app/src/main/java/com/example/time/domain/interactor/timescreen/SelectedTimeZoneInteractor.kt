package com.example.time.domain.interactor.timescreen

import com.example.time.data.db.timescreen.entity.TimeDataEntity
import kotlinx.coroutines.flow.Flow

interface SelectedTimeZoneInteractor {
    suspend fun insertSelectedTimeData(data: TimeDataEntity)
    fun getSelectedTimeData(): Flow<List<TimeDataEntity>>
    suspend fun deleteSelectedTimezone(timezone: String)
}
