package com.example.time.app.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.time.R

sealed class Screens(
    val route: String,
    @param:DrawableRes val iconResId: Int,
    @param:StringRes val title: Int
) {

    object SettingsScreen : Screens(
        route = "settings",
        iconResId = R.drawable.ic_settings,
        title = R.string.settings
    )

    object SearchScreen : Screens(
        route = "search",
        iconResId = R.drawable.ic_search,
        title = R.string.search
    )

    object BackScreen : Screens(
        route = "back",
        iconResId = R.drawable.ic_arrow_back,
        title = R.string.back
    )

    companion object {
        val items = listOf(
            SettingsScreen,
            SearchScreen
        )
    }
}