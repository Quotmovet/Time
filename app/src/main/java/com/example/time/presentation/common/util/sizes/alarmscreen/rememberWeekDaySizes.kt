package com.example.time.presentation.common.util.sizes.alarmscreen

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp

@SuppressLint("ConfigurationScreenWidthHeight")
@Composable
fun rememberWeekDaySizes(): WeekDaySizes {
    val configuration = LocalConfiguration.current
    val density = LocalDensity.current

    val screenWidthDp = configuration.screenWidthDp
    val fontScale = density.fontScale.coerceIn(0.85f, 1.2f)

    return remember(screenWidthDp, fontScale) {
        when {
            screenWidthDp < 320 -> WeekDaySizes(buttonSize = 36.dp)
            screenWidthDp < 360 -> WeekDaySizes(buttonSize = 40.dp)
            screenWidthDp < 380 -> WeekDaySizes(buttonSize = 42.dp)
            screenWidthDp < 400 -> WeekDaySizes(buttonSize = 44.dp)
            screenWidthDp < 420 -> WeekDaySizes(buttonSize = 48.dp)
            screenWidthDp < 480 -> WeekDaySizes(buttonSize = 50.dp)
            screenWidthDp < 600 -> WeekDaySizes(buttonSize = 60.dp)
            else -> WeekDaySizes(buttonSize = 68.dp)
        }
    }
}
