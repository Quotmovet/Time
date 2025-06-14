package com.example.time.domain.model.alarmscreen

data class AlarmModel(
    val id: Int,
    val hour: Int,
    val minute: Int,
    val days: String,
    val isActivated: Boolean,
    val isVibration: Boolean,
    val sound: String,
    val name: String
)
