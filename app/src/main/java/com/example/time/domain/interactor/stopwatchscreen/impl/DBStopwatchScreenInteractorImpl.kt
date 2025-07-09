package com.example.time.domain.interactor.stopwatchscreen.impl

import com.example.time.domain.interactor.stopwatchscreen.DBStopwatchScreenInteractor
import com.example.time.domain.model.stopwatchscreen.StopwatchModel
import com.example.time.domain.repositories.stopwatchscreen.DBStopwatchScreenRepository
import javax.inject.Inject

class DBStopwatchScreenInteractorImpl
    @Inject
    constructor(
        private val dBStopwatchScreenRepository: DBStopwatchScreenRepository
    ) : DBStopwatchScreenInteractor {
        override suspend fun insertStopwatch(model: StopwatchModel) {
            dBStopwatchScreenRepository.insertStopwatch(model)
        }

        override suspend fun getAllStopwatches(): List<StopwatchModel> {
            return dBStopwatchScreenRepository.getAllStopwatches()
        }

        override suspend fun resetStopwatches() {
            dBStopwatchScreenRepository.resetStopwatches()
        }
    }