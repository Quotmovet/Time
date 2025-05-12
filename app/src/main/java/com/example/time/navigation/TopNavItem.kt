package com.example.time.navigation

import androidx.annotation.DrawableRes
import com.example.time.R

sealed class TopNavItem(
    val route: String,
    @DrawableRes val iconResId: Int,
    val title: String
) {

    object SettingsScreen : BottomNavItem(
        route = "settings",
        iconResId = R.drawable.ic_settings,
        title = "Settings"
    )

    object BackScreen : BottomNavItem(
        route = "back",
        iconResId = R.drawable.ic_arrow_back,
        title = "Back"
    )

    companion object {
        val items = listOf(
            SettingsScreen
        )
    }
}