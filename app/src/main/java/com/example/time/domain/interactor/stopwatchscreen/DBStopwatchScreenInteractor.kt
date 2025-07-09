package com.example.time.domain.interactor.stopwatchscreen

import com.example.time.domain.model.stopwatchscreen.StopwatchModel

interface DBStopwatchScreenInteractor {
    suspend fun insertStopwatch(model: StopwatchModel)
    suspend fun getAllStopwatches(): List<StopwatchModel>
    suspend fun resetStopwatches()
}