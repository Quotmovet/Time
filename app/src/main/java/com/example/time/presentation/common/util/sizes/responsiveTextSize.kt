package com.example.time.presentation.common.util.sizes

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.TextUnit
import com.example.time.presentation.common.Dimens.PrimaryTextSize16
import com.example.time.presentation.common.Dimens.TextSize12
import com.example.time.presentation.common.Dimens.TextSize14

@SuppressLint("ConfigurationScreenWidthHeight")
@Composable
fun responsiveTextSize(): TextUnit {
    val configuration = LocalConfiguration.current
    return when {
        configuration.screenWidthDp < 240 -> TextSize12
        configuration.screenWidthDp < 640 -> TextSize14
        else -> PrimaryTextSize16
    }
}