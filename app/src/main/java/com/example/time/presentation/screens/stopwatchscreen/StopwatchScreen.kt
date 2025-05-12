package com.example.time.presentation.screens.stopwatchscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.time.presentation.theme.Dimens.TextSize24

@Composable
fun StopwatchScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Stopwatch Screen",
            fontSize = TextSize24,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}