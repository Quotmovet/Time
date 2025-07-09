package com.example.time.presentation.ui.components.timerscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TimerBoxes(
    onHourClick: () -> Unit,
    onMinuteClick: () -> Unit,
    onSecondClick: () -> Unit
) {
    Row {
        Box(
            modifier = Modifier
                .padding(start = 40.dp)
                .background(color = MaterialTheme.colorScheme.surface, shape = CircleShape)
                .clickable(onClick = onHourClick)
        ) {

        }

        Box(
            modifier = Modifier
                .padding(horizontal = 20.dp)
        ) {

        }

        Box(
            modifier = Modifier
                .padding(end = 40.dp)
        ) {

        }
    }
}