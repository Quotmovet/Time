package com.example.time.data.db.stopwatchscreen.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stopwatch")
data class StopwatchEntity (
    @PrimaryKey(autoGenerate = true) val id: Int,
    val mainTime: String,
    val lapTime: String
)