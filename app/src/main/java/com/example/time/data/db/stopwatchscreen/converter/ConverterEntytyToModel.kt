package com.example.time.data.db.stopwatchscreen.converter

import com.example.time.data.db.stopwatchscreen.entity.StopwatchEntity
import com.example.time.domain.model.stopwatchscreen.StopwatchModel

fun StopwatchModel.toEntityFromModel(): StopwatchEntity {
    return StopwatchEntity(
        id = this.id,
        mainTime = this.mainTime,
        lapTime = this.lapTime
    )
}

fun StopwatchEntity.toModelFromEntity(): StopwatchModel {
    return StopwatchModel(
        id = this.id,
        mainTime = this.mainTime,
        lapTime = this.lapTime
    )
}