package com.example.time.presentation.state.stopwatchscreen

data class StopwatchUiState(
    val elapsedTimeMillis: Long = 0L,
    val progress: Float = 0f,
    val isRunning: Boolean = false
)