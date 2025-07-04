package com.example.time.presentation.common.util.formater

fun formatOffset(offsetMinutes: Int): String {
    val hours = offsetMinutes / 60
    val sign = if (hours >= 0) "+" else "-"
    return "$sign${kotlin.math.abs(hours)} h."
}
