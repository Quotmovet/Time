package com.example.time.presentation.ui.components.stopwatchscreen

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun CircularStopwatchWithLapAnimation() {
    var isRunning by remember { mutableStateOf(false) }
    var elapsedTimeMillis by remember { mutableLongStateOf(0L) }
    var lastLapTimeMillis by remember { mutableLongStateOf(0L) }

    val coroutineScope = rememberCoroutineScope()
    val progress = remember { Animatable(0f) }

    // Таймер — обновляется каждые 16ms (~60 FPS)
    LaunchedEffect(isRunning) {
        val frameDelay = 16L
        while (isRunning) {
            delay(frameDelay)
            elapsedTimeMillis += frameDelay
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = Modifier.size(215.dp), contentAlignment = Alignment.Center) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                val strokeWidth = 12.dp.toPx()
                val radius = size.minDimension / 2 - strokeWidth / 2
                val center = Offset(size.width / 2, size.height / 2)

                drawCircle(
                    color = Color.LightGray,
                    center = center,
                    radius = radius,
                    style = Stroke(width = strokeWidth)
                )

                val sweepAngle = 360f * progress.value
                drawArc(
                    color = Color(0xFF4A86E8),
                    startAngle = -90f,
                    sweepAngle = sweepAngle,
                    useCenter = false,
                    topLeft = Offset(center.x - radius, center.y - radius),
                    size = Size(radius * 2, radius * 2),
                    style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
                )
            }

            // Расчёт времени
            val totalSeconds = elapsedTimeMillis / 1000
            val minutes = totalSeconds / 60
            val seconds = (totalSeconds % 60).toInt()
            val hundredths = ((elapsedTimeMillis % 1000) / 10).toInt() // 0-99

            val topText = if (minutes > 0)
                String.format("%02d:%02d", minutes, seconds)
            else
                String.format("%02d", seconds)

            val bottomText = String.format("%02d", hundredths)

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = topText,
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = bottomText,
                    fontSize = 20.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Button(onClick = {
                isRunning = true
            }) {
                Text("Старт")
            }

            Button(onClick = {
                val lapDurationMillis = elapsedTimeMillis - lastLapTimeMillis
                if (lapDurationMillis <= 0) return@Button

                lastLapTimeMillis = elapsedTimeMillis

                coroutineScope.launch {
                    progress.snapTo(0f)
                    progress.animateTo(
                        targetValue = 1f,
                        animationSpec = tween(durationMillis = lapDurationMillis.toInt())
                    )
                }
            }) {
                Text("Отсечка")
            }
        }
    }
}

