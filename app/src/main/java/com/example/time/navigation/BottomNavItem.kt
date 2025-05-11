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
        iconResId = R.drawable.alarm,
        title = "Alarm"
    )

    object TimerScreen : BottomNavItem(
        route = "timer",
        iconResId = R.drawable.timer,
        title = "Timer"
    )

    object TimeScreen: BottomNavItem(
        route = "time",
        iconResId = R.drawable.world_time,
        title = "Time"
    )

    object StopwatchScreen : BottomNavItem(
        route = "stopwatch",
        iconResId = R.drawable.stopwatch,
        title = "Stopwatch"
    )

    object SleepScreen : BottomNavItem(
        route = "sleep",
        iconResId = R.drawable.sleep,
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