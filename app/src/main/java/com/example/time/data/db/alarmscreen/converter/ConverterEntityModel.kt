package com.example.time.data.db.alarmscreen.converter

import com.example.time.data.db.alarmscreen.entity.AlarmEntity
import com.example.time.domain.model.alarmscreen.AlarmModel

fun AlarmModel.toEntityFromModel(): AlarmEntity {
    return AlarmEntity(
        id = this.id,
        hour = this.hour,
        minute = this.minute,
        days = this.days,
        isActivated = this.isActivated,
        isVibration = this.isVibration,
        sound = this.sound,
        name = this.name
    )
}

fun AlarmEntity.toModelFromEntity(): AlarmModel {
    return AlarmModel(
        id = this.id,
        hour = this.hour,
        minute = this.minute,
        days = this.days,
        isActivated = this.isActivated,
        isVibration = this.isVibration,
        sound = this.sound,
        name = this.name
    )
}