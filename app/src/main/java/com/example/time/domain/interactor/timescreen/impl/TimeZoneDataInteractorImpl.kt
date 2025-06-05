package com.example.time.domain.interactor.timescreen.impl

import com.example.time.data.dto.Resource
import com.example.time.domain.interactor.timescreen.TimeZoneDataInteractor
import com.example.time.domain.model.timescreen.TimeDataModel
import com.example.time.domain.repositories.timescreen.TimeDataRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TimeZoneDataInteractorImpl @Inject constructor (
    private val timeDataRepository: TimeDataRepository
): TimeZoneDataInteractor {
    override suspend fun getTimeZone(expression: String): Flow<Resource<List<TimeDataModel>>> {
        return timeDataRepository.getTimeData(expression)
    }
}