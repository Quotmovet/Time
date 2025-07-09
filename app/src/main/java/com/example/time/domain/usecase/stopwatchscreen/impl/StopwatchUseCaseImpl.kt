package com.example.time.domain.usecase.stopwatchscreen.impl

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import com.example.time.domain.usecase.stopwatchscreen.StopwatchUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class StopwatchUseCaseImpl
    @Inject
    constructor() : StopwatchUseCase {
        private var isRunning = false

        override suspend fun start(onTick: (Long) -> Unit) {
            isRunning = true
            var time = 0L
            while (isRunning) {
                delay(16)
                time += 16
                onTick(time)
            }
        }

        override fun stop() {
            isRunning = false
        }

        override fun animateLap(duration: Long, onUpdate: (Float) -> Unit) {
            CoroutineScope(Dispatchers.Default).launch {
                val animatable = Animatable(0f)
                animatable.snapTo(0f)
                animatable.animateTo(
                    1f,
                    animationSpec = tween(durationMillis = duration.toInt())
                ) {
                    onUpdate(value)
                }
            }
        }
    }