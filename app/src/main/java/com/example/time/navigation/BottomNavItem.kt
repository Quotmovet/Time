package com.example.time.navigation

import androidx.annotation.DrawableRes
import com.example.time.R

sealed class BottomNavItem(
    val route: String,
    @DrawableRes val iconResId: Int,
    val title: String
) {

    object AlarmScreen : BottomNavItem(
        route = "alarm",
        iconResId = R.drawable.ic_alarm,
        title = "Alarm"
    )

    object TimerScreen : BottomNavItem(
        route = "timer",
        iconResId = R.drawable.ic_timer,
        title = "Timer"
    )

    object TimeScreen: BottomNavItem(
        route = "time",
        iconResId = R.drawable.ic_world_time,
        title = "Time"
    )

    object StopwatchScreen : BottomNavItem(
        route = "stopwatch",
        iconResId = R.drawable.ic_stopwatch,
        title = "Stopwatch"
    )

    object SleepScreen : BottomNavItem(
        route = "sleep",
        iconResId = R.drawable.ic_sleep,
        title = "Sleep"
    )

    companion object {
        val items = listOf(
            AlarmScreen,
            TimerScreen,
            TimeScreen,
            StopwatchScreen,
            SleepScreen
        )
    }
}