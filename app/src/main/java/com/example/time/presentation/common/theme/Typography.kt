package com.example.time.presentation.common.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.example.time.R
import com.example.time.presentation.common.Dimens.PrimaryTextSize16
import com.example.time.presentation.common.Dimens.TextSize10
import com.example.time.presentation.common.Dimens.TextSize12
import com.example.time.presentation.common.Dimens.TextSize22
import com.example.time.presentation.common.Dimens.TextSize30
import com.example.time.presentation.common.Dimens.TextSize32
import com.example.time.presentation.common.Dimens.TextSize48
import com.example.time.presentation.common.Dimens.TextSize64

val Geist = FontFamily(

    // Bold
    Font(R.font.geist_display_bold, FontWeight.Bold),
    Font(R.font.geist_display_semibold, FontWeight.SemiBold),
    Font(R.font.geist_display_extrabold, FontWeight.ExtraBold),

    // Medium
    Font(R.font.geist_display_regular, FontWeight.Normal),
    Font(R.font.geist_display_medium, FontWeight.Medium),

    // Thin
    Font(R.font.geist_display_light, FontWeight.Light),
    Font(R.font.geist_display_extralight, FontWeight.ExtraLight),
)

val Typography = Typography(

    // Headline
    headlineLarge = TextStyle(
        fontFamily = Geist,
        fontWeight = FontWeight.Bold,
        fontSize = TextSize64
    ),
    headlineMedium = TextStyle(
        fontFamily = Geist,
        fontWeight = FontWeight.Bold,
        fontSize = TextSize48
    ),
    headlineSmall = TextStyle(
        fontFamily = Geist,
        fontWeight = FontWeight.Bold,
        fontSize = TextSize32
    ),

    // Title
    titleLarge = TextStyle(
        fontFamily = Geist,
        fontWeight = FontWeight.Medium,
        fontSize = TextSize30
    ),
    titleMedium = TextStyle(
        fontFamily = Geist,
        fontWeight = FontWeight.Medium,
        fontSize = TextSize22
    ),
    titleSmall = TextStyle(
        fontFamily = Geist,
        fontWeight = FontWeight.Medium,
        fontSize = PrimaryTextSize16
    ),

    // Label
    labelLarge = TextStyle(
        fontFamily = Geist,
        fontWeight = FontWeight.Normal,
        fontSize = PrimaryTextSize16
    ),
    labelMedium = TextStyle(
        fontFamily = Geist,
        fontWeight = FontWeight.Normal,
        fontSize = TextSize12
    ),
    labelSmall = TextStyle(
        fontFamily = Geist,
        fontWeight = FontWeight.Normal,
        fontSize = TextSize10
    ),
)