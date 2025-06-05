package com.example.time.domain.interactor.timescreen

import com.example.time.data.dto.Resource
import com.example.time.domain.model.timescreen.TimeDataModel
import kotlinx.coroutines.flow.Flow

interface TimeZoneDataInteractor {
    suspend fun getTimeZone(expression: String): Flow<Resource<List<TimeDataModel>>>
}