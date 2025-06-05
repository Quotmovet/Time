package com.example.time.domain.repositories.timescreen

interface CurrentTimeRepository {
    fun getCurrentTime(): String
    fun getCurrentDate(): String
}