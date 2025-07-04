package com.example.time.presentation.viewmodel.searchscreen

import com.example.time.domain.model.timescreen.TimeDataModel

data class SearchScreenState(
    val timeZone: List<TimeDataModel> = emptyList(),
    val isLoading: Boolean,
    val isFailed: Boolean?,
    val isEmpty: Boolean = false,
)
