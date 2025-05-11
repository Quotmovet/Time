package com.example.time

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.Surface
import com.example.time.presentation.screens.navigation.MainScreen
import com.example.time.presentation.theme.Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Theme(
                darkTheme = isSystemInDarkTheme(),
                dynamicColor = false
            ) {
                Surface{
                    MainScreen()
                }
            }
        }
    }
}