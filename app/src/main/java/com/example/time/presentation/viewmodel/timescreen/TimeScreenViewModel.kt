package com.example.time.presentation.viewmodel.timescreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.time.domain.usecase.timescreen.GetCurrentDateUseCase
import com.example.time.domain.usecase.timescreen.GetCurrentTimeUseCase
import com.example.time.presentation.common.util.Constants.ONE_MINUTE
import com.example.time.presentation.common.util.Constants.ONE_SECOND
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TimeScreenViewModel @Inject constructor (
    private val getCurrentTimeUseCase: GetCurrentTimeUseCase,
    private val getCurrentDateUseCase: GetCurrentDateUseCase
) : ViewModel() {

    private val _currentTime = mutableStateOf(getCurrentTimeUseCase())
    val currentTime: State<String> = _currentTime

    private val _currentDate = mutableStateOf(getCurrentDateUseCase())
    val currentDate: State<String> = _currentDate

    init {
        startTimeUpdates()
        startDateUpdates()
    }

    private fun startTimeUpdates() {
        viewModelScope.launch {
            while (true) {
                delay(ONE_SECOND)
                _currentTime.value = getCurrentTimeUseCase()
            }
        }
    }

    private fun startDateUpdates() {
        viewModelScope.launch {
            while (true) {
                delay(ONE_MINUTE)
                _currentDate.value = getCurrentDateUseCase()
            }
        }
    }
}