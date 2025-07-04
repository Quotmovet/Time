package com.example.time.domain.repositories.timescreen

import com.example.time.domain.model.timescreen.TimeDataModel
import kotlinx.coroutines.flow.Flow

interface DataSourceTimeRepository {
    suspend fun getTimeData(expression: String): Flow<List<TimeDataModel>>
}
