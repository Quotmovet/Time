package com.example.time.presentation.viewmodel.stopwatchscreen

import androidx.lifecycle.ViewModel
import com.example.time.domain.usecase.stopwatchscreen.StopwatchUseCase
import com.example.time.domain.interactor.stopwatchscreen.DBStopwatchScreenInteractor
import com.example.time.presentation.state.stopwatchscreen.StopwatchUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class StopwatchScreenViewModel
    @Inject
    constructor(
        private val dBStopwatchScreenInteractor: DBStopwatchScreenInteractor,
        private val stopwatchUseCase: StopwatchUseCase
    ) : ViewModel() {
        private val _uiState = MutableStateFlow(StopwatchUiState())
        val uiState: StateFlow<StopwatchUiState> = _uiState

        private var lastLapTime = 0L

        suspend fun start() {
            stopwatchUseCase.start { elapsed ->
                _uiState.update {
                    it.copy(elapsedTimeMillis = elapsed, isRunning = true)
                }
            }
        }

        fun lap() {
            val current = _uiState.value.elapsedTimeMillis
            val duration = current - lastLapTime
            lastLapTime = current

            stopwatchUseCase.animateLap(duration) { progress ->
            _uiState.update { it.copy(progress = progress) }
            }
        }
    }