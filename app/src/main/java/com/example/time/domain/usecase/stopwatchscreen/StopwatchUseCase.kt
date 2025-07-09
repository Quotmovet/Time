package com.example.time.domain.usecase.stopwatchscreen

interface StopwatchUseCase {
    suspend fun start(onTick: (Long) -> Unit)
    fun stop()
    fun animateLap(duration: Long, onUpdate: (Float) -> Unit)
}