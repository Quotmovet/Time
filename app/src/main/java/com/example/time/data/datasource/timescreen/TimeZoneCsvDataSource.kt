package com.example.time.data.datasource.timescreen

import android.content.Context
import android.util.Log
import com.example.time.data.datasource.timescreen.dto.TimeZoneCsvDto
import java.time.Instant
import java.time.ZoneId
import javax.inject.Inject

class TimeZoneCsvDataSource @Inject constructor (private val context: Context) {
    fun readTimeZonesFromCsv(): List<TimeZoneCsvDto> {
        return try {
            val data = context.assets.open("timezones.csv")
                .bufferedReader()
                .useLines { lines ->
                    lines
                        .drop(1)
                        .mapNotNull { line -> parseCsvLine(line) }
                        .toList()
                }
            data
        } catch (e: Exception) {
            Log.e("TimeZoneCsvDataSource:","Failed to load CSV: ${e.localizedMessage}")
            emptyList()
        }
    }

    private fun parseCsvLine(line: String): TimeZoneCsvDto? {
        val tokens = line.split(",")
        if (tokens.size < 4) {
            Log.e("TimeZoneCsvDataSource:","Skipped line (not enough tokens): $line")
            return null
        }

        return try {
            val timeZone = tokens[0].trim()
            val cityName = tokens[3].trim()
            val country = tokens[2].trim()

            val offsetMinutes = ZoneId.of(timeZone)
                .rules.getOffset(Instant.now())
                .totalSeconds / 60

            TimeZoneCsvDto(
                timeZone = timeZone,
                cityName = cityName,
                country = country,
                offset = offsetMinutes,
                isSelected = false
            )
        } catch (e: Exception) {
            Log.e("TimeZoneCsvDataSource:","Failed to parse line: $line — ${e.message}")
            null
        }
    }
}
