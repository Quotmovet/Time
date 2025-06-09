package com.example.time.data.datasource.timescreen.converter

import com.example.time.presentation.common.util.formater.formatTime
import com.example.time.data.datasource.timescreen.dto.TimeZoneCsvDto
import com.example.time.domain.model.timescreen.TimeDataModel

fun TimeZoneCsvDto.toModelFromCsvDto(): TimeDataModel {
    return TimeDataModel(
        timeZone = this.timeZone,
        cityName = this.cityName,
        country = this.country,
        time = formatTime(this.offset),
        offset = this.offset,
        isSelected = false
    )
}
