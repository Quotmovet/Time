package com.example.time.data.db.alarmscreen.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "alarms")
data class AlarmEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val hour: Int,
    val minute: Int,
    val days: String,
    val isActivated: Boolean,
    val isVibration: Boolean,
    val sound: String,
    val name: String
)
