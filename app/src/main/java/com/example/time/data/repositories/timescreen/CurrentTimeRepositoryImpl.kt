package com.example.time.data.repositories.timescreen

import com.example.time.domain.repositories.timescreen.CurrentTimeRepository
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class CurrentTimeRepositoryImpl @Inject constructor(): CurrentTimeRepository {

    override fun getCurrentTimeWithMills(): String {
        return SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date())
    }

    override fun getCurrentTime(): String {
        return SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())
    }

    override fun getCurrentDate(): String {
        return SimpleDateFormat("EEE, dd MMM", Locale.getDefault()).format(Date())
    }

}
