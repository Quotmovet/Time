package com.example.time.presentation.common.theme

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
    primary = PrimaryD,
    onPrimary = OnPrimaryD,
    primaryContainer = PrimaryContainerD,
    onPrimaryContainer = OnPrimaryContainerD,

    secondary = SecondaryD,
    onSecondary = OnSecondaryD,

    tertiary = TertiaryD,
    onTertiary = OnTertiaryD,

    background = BackgroundD,
    onBackground = OnBackgroundD,

    surface = SurfaceD,
    onSurface = OnSurfaceD,
    surfaceVariant = SurfaceVariantD,
    onSurfaceVariant = OnSurfaceVariantD,

    error = ErrorLD,
    onError = OnErrorLD,
    errorContainer = ErrorContainerLD,
    onErrorContainer = OnErrorContainerLD,

    outline = OutlineD,
    outlineVariant = OutlineVariantD
)


private val LightColorScheme = lightColorScheme(
    primary = PrimaryL,
    onPrimary = OnPrimaryL,
    primaryContainer = PrimaryContainerL,
    onPrimaryContainer = OnPrimaryContainerL,

    secondary = SecondaryL,
    onSecondary = OnSecondaryL,

    tertiary = TertiaryL,
    onTertiary = OnTertiaryL,

    background = BackgroundL,
    onBackground = OnBackgroundL,

    surface = SurfaceL,
    onSurface = OnSurfaceL,
    surfaceVariant = SurfaceVariantL,
    onSurfaceVariant = OnSurfaceVariantL,

    error = ErrorLD,
    onError = OnErrorLD,
    errorContainer = ErrorContainerLD,
    onErrorContainer = OnErrorContainerLD,

    outline = OutlineL,
    outlineVariant = OutlineVariantL
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