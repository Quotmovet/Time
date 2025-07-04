package com.example.time.presentation.screens.sleepscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.time.presentation.common.Dimens.TextSize24

@Composable
fun SleepScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = "Sleep Screen",
            fontSize = TextSize24,
            color = MaterialTheme.colorScheme.onPrimary,
        )
    }
}
