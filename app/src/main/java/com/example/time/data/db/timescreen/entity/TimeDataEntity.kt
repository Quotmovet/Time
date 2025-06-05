package com.example.time.data.db.timescreen.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "time_data")
data class TimeDataEntity (
    @PrimaryKey val timeZone: String,
    val cityName: String,
    val country: String,
    val time: String,
    val isSelected: Boolean
)
