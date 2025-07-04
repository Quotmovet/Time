package com.example.time.domain.interactor.timescreen.impl

import com.example.time.data.db.timescreen.entity.TimeDataEntity
import com.example.time.domain.interactor.timescreen.SelectedTimeZoneInteractor
import com.example.time.domain.repositories.timescreen.SelectedTimeZoneRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SelectedTimeZoneInteractorImpl
    @Inject
    constructor(
        private val selectedTimeZoneRepository: SelectedTimeZoneRepository,
    ) : SelectedTimeZoneInteractor {
        override suspend fun insertSelectedTimeData(data: TimeDataEntity) {
            selectedTimeZoneRepository.insertSelectedTimeData(data)
        }

        override fun getSelectedTimeData(): Flow<List<TimeDataEntity>> {
            return selectedTimeZoneRepository.getSelectedTimeData()
        }

        override suspend fun deleteSelectedTimezone(timezone: String) {
            selectedTimeZoneRepository.deleteSelectedTimezone(timezone)
        }
    }
