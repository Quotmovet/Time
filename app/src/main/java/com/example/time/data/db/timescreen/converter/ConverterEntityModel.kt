package com.example.time.data.db.timescreen.converter

import com.example.time.data.db.timescreen.entity.TimeDataEntity
import com.example.time.domain.model.timescreen.TimeDataModel

fun TimeDataModel.toEntityFromModel(): TimeDataEntity {
    return TimeDataEntity(
        timeZone = this.timeZone,
        cityName = this.cityName,
        country = this.country,
        time = this.time,
        offset = this.offset,
        isSelected = true
    )
}

fun TimeDataEntity.toModelFromEntity(): TimeDataModel {
    return TimeDataModel(
        timeZone = this.timeZone,
        cityName = this.cityName,
        country = this.country,
        time = this.time,
        offset = this.offset,
        isSelected = true
    )
}