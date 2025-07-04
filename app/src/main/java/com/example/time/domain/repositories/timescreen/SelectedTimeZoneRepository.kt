package com.example.time.domain.repositories.timescreen

import com.example.time.data.db.timescreen.entity.TimeDataEntity
import kotlinx.coroutines.flow.Flow

interface SelectedTimeZoneRepository {
    suspend fun insertSelectedTimeData(data: TimeDataEntity)

    fun getSelectedTimeData(): Flow<List<TimeDataEntity>>

    suspend fun deleteSelectedTimezone(timezone: String)
}
