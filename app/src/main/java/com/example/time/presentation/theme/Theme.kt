package com.example.time.presentation.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = White,
    onPrimary = White, // Цвет текста/иконок на primary
    primaryContainer = LightBlack, // Контейнер для primary (например, фон кнопок)
    onPrimaryContainer = White, // Цвет содержимого в primaryContainer

    secondary = Blue,
    onSecondary = Blue,

    background = Black,
    onBackground = White, // Цвет текста на фоне background

    surface = Black,
    onSurface = White, // Цвет текста на surface
    surfaceContainer = Black,

    surfaceVariant = Gray, // Вариант поверхности (например, карточки)
    onSurfaceVariant = White,

    error = Red, // Цвет для ошибок
    onError = White
)


private val LightColorScheme = lightColorScheme(
    primary = White,
    onPrimary = Black,
    primaryContainer = LightGray,
    onPrimaryContainer = Black,

    secondary = Blue,
    onSecondary = Blue,

    background = White,
    onBackground = Black,

    surface = White,
    onSurface = Black,
    surfaceContainer = White,

    surfaceVariant = LightGray,
    onSurfaceVariant = Black,

    error = Red,
    onError = White
)

@Composable
fun Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}