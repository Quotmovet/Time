package com.example.time.domain.repositories.timescreen

interface TimesRepository {
    fun getCurrentTime(): String
    fun getCurrentDate(): String
}