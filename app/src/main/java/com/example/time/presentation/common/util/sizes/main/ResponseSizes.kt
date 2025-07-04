package com.example.time.presentation.common.util.sizes.main

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import com.example.time.presentation.common.Dimens.BigIconsSize36
import com.example.time.presentation.common.Dimens.BigIconsSize40
import com.example.time.presentation.common.Dimens.BigIconsSize44
import com.example.time.presentation.common.Dimens.BigIconsSize52
import com.example.time.presentation.common.Dimens.SmallIconsSize28
import com.example.time.presentation.common.Dimens.SmallIconsSize32
import com.example.time.presentation.common.Dimens.TextSize10
import com.example.time.presentation.common.Dimens.TextSize11
import com.example.time.presentation.common.Dimens.TextSize13
import com.example.time.presentation.common.Dimens.TextSize14
import com.example.time.presentation.common.Dimens.TextSize16
import com.example.time.presentation.common.Dimens.TextSize9

@SuppressLint("ConfigurationScreenWidthHeight")
@Composable
fun rememberResponsiveSizes(): ResponsiveSizes {
    val configuration = LocalConfiguration.current
    val density = LocalDensity.current

    val screenWidthDp = configuration.screenWidthDp
    val fontScale = density.fontScale

    val iconSize =
        when {
            screenWidthDp < 320 -> SmallIconsSize28
            screenWidthDp < 360 -> SmallIconsSize32
            screenWidthDp < 420 -> BigIconsSize36
            screenWidthDp < 480 -> BigIconsSize40
            screenWidthDp < 600 -> BigIconsSize44
            else -> BigIconsSize52
        }

    val baseTextSize =
        when {
            screenWidthDp < 320 -> TextSize9
            screenWidthDp < 360 -> TextSize10
            screenWidthDp < 420 -> TextSize11
            screenWidthDp < 480 -> TextSize13
            screenWidthDp < 600 -> TextSize14
            else -> TextSize16
        }

    val fontScaleAdjusted = fontScale.coerceIn(0.8f, 1.3f)
    val adjustedTextSize = baseTextSize * fontScaleAdjusted

    return remember(screenWidthDp, fontScale) {
        ResponsiveSizes(
            iconSize = iconSize,
            textSize = adjustedTextSize,
        )
    }
}
