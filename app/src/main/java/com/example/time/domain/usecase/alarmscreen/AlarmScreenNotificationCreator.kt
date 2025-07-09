package com.example.time.domain.usecase.alarmscreen

import android.app.Notification

interface AlarmScreenNotificationCreator {
    fun create(
        alarmId: Int,
        alarmName: String,
        alarmDate: String,
    ): Notification
}
