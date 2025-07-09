package com.example.time.domain.repositories.stopwatchscreen

import com.example.time.domain.model.stopwatchscreen.StopwatchModel

interface DBStopwatchScreenRepository {
    suspend fun insertStopwatch(model: StopwatchModel)

    suspend fun getAllStopwatches(): List<StopwatchModel>

    suspend fun resetStopwatches()
}