package com.example.time.app.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.time.R

sealed class BottomNavItem(
    val route: String,
    @param:DrawableRes val iconResId: Int,
    @param:StringRes val title: Int,
) {
    object AlarmScreen : BottomNavItem(
        route = "alarm",
        iconResId = R.drawable.ic_alarm,
        title = R.string.alarm,
    )

    object TimerScreen : BottomNavItem(
        route = "timer",
        iconResId = R.drawable.ic_timer,
        title = R.string.timer,
    )

    object TimeScreen : BottomNavItem(
        route = "time",
        iconResId = R.drawable.ic_world_time,
        title = R.string.time,
    )

    object StopwatchScreen : BottomNavItem(
        route = "stopwatch",
        iconResId = R.drawable.ic_stopwatch,
        title = R.string.stopwatch,
    )

    object SleepScreen : BottomNavItem(
        route = "sleep",
        iconResId = R.drawable.ic_sleep,
        title = R.string.sleep,
    )

    companion object {
        val items =
            listOf(
                AlarmScreen,
                TimerScreen,
                TimeScreen,
                StopwatchScreen,
                SleepScreen,
            )
    }
}
