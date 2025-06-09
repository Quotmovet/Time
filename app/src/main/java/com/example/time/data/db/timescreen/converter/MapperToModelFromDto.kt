package com.example.time.data.db.timescreen.converter

import android.annotation.SuppressLint
import com.example.time.data.dto.timescreen.TimeDataDto
import com.example.time.domain.model.timescreen.TimeDataModel

fun TimeDataDto.toModelFromDto(): TimeDataModel {
    return TimeDataModel(
        timeZone = this.timeZone,
        cityName = parseCityName(this.timeZone),
        country = parseCountry(this.timeZone),
        time = formatTime(this.hour, this.minute),
        offset = 0,
        isSelected = false
    )
}

private fun parseCityName(timeZone: String): String {
    return if (timeZone.contains('/')) {
        timeZone.substringAfterLast('/').replace("_", " ")
    } else {
        timeZone.replace("_", " ")
    }.ifEmpty { "Unknown City" }
}

private fun parseCountry(timeZone: String): String {
    return if (timeZone.contains('/')) {
        timeZone.substringBefore('/').replace("_", " ")
    } else {
        "Unknown Country"
    }
}

@SuppressLint("DefaultLocale")
private fun formatTime(hour: Int, minute: Int): String {
    return String.format("%02d:%02d", hour, minute)
}
