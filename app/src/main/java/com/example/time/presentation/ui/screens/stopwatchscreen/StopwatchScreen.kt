package com.example.time.presentation.ui.screens.stopwatchscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.time.presentation.ui.common.theme.Theme
import com.example.time.presentation.ui.components.stopwatchscreen.CircularStopwatchWithLapAnimation

@Composable
fun StopwatchScreen() {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        ) {
        CircularStopwatchWithLapAnimation()
    }
}

@Preview
@Composable
fun StopwatchScreenPreview() {
    Theme {
        StopwatchScreen()
    }
}
