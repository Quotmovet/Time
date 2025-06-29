package com.example.time.domain.repositories.timescreen

interface CurrentTimeRepository {
    fun getCurrentTimeWithMills(): String
    fun getCurrentTime(): String
    fun getCurrentDate(): String
}