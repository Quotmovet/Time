package com.example.time.data.network.timescreen

import com.example.time.data.dto.timescreen.TimeDataDto
import retrofit2.http.GET
import retrofit2.http.Query

interface TimeAPI {
    @GET("/api/now")
    suspend fun getTimeZoneData(
        @Query("tz") timeZone: String,
    ): TimeDataDto
}
