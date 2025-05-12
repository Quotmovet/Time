package com.example.time.util.sizes

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import com.example.time.presentation.theme.Dimens.BigIconsSize36
import com.example.time.presentation.theme.Dimens.BigIconsSize48
import com.example.time.presentation.theme.Dimens.PrimaryIconsSize

@SuppressLint("ConfigurationScreenWidthHeight")
@Composable
fun responsiveIconSize(): Dp {
    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp

    return when {
        screenWidthDp < 240 -> PrimaryIconsSize
        screenWidthDp < 640 -> BigIconsSize36
        else -> BigIconsSize48
    }
}
