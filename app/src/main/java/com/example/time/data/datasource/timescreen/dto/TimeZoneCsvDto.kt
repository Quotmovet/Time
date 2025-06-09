package com.example.time.data.datasource.timescreen.dto

data class TimeZoneCsvDto(
    val timeZone: String,
    val cityName: String,
    val country: String,
    val offset: Int,
    var isSelected: Boolean
)
