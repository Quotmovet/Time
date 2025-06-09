package com.example.time.domain.repositories.timescreen

import com.example.time.data.dto.Resource
import com.example.time.domain.model.timescreen.TimeDataModel
import kotlinx.coroutines.flow.Flow

interface TimeDataRepository {
    suspend fun getTimeData(expression: String): Flow<Resource<List<TimeDataModel>>>
}
