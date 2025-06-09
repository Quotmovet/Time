package com.example.time.presentation.common.util.formater

import java.time.Instant
import java.time.ZoneOffset
import java.util.Locale

fun formatTime(offsetMinutes: Int): String {
    val nowUtc = Instant.now()
    val zoneOffset = ZoneOffset.ofTotalSeconds(offsetMinutes * 60)
    val zonedDateTime = nowUtc.atOffset(zoneOffset).toLocalTime()

    val hour = zonedDateTime.hour
    val minute = zonedDateTime.minute

    return String.format(Locale.ROOT, "%02d:%02d", hour, minute)
}
