package com.example.time.domain.model.timescreen

data class TimeDataModel(
    val timeZone: String,
    val cityName: String,
    val country: String,
    val time: String,
    val offset: Int,
    var isSelected: Boolean,
)
