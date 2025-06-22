package com.example.time.presentation.components.alarmscreen.alarmScreenDuringAlarm

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.time.R
import com.example.time.presentation.common.theme.Theme
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import com.example.time.presentation.common.Dimens.BigIconsSize44
import com.example.time.presentation.common.Dimens.LargeIconsSize134
import com.example.time.presentation.common.Dimens.LargeIconsSize78
import com.example.time.presentation.common.Dimens.Offset30
import kotlin.math.roundToInt
import androidx.compose.runtime.*
import androidx.compose.ui.input.pointer.pointerInput
import kotlinx.coroutines.launch

@Composable
fun AlarmOffPostpone(
    onPostponeClick: () -> Unit,
    onOff: () -> Unit,
) {
    val density = LocalDensity.current

    val offsetSidePx = with(density) { Offset30.toPx() }

    val animatableOffsetX = remember { Animatable(0f) }
    var highlightLeft by remember { mutableStateOf(false) }
    var highlightRight by remember { mutableStateOf(false) }

    val coroutineScope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(LargeIconsSize134)
    ) {
        // Postpone
        Box(
            modifier = Modifier
                .size(LargeIconsSize134)
                .offset { IntOffset(x = (-offsetSidePx).roundToInt(), y = 0) }
                .background(
                    color = if (highlightLeft) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.primaryContainer,
                    shape = CircleShape,
                )
                .clickable { onPostponeClick() },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_postpone),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.tertiary,
                modifier = Modifier.size(BigIconsSize44)
            )
        }

        // Off
        Box(
            modifier = Modifier
                .size(LargeIconsSize134)
                .align(Alignment.CenterEnd)
                .offset { IntOffset(x = offsetSidePx.roundToInt(), y = 0) }
                .background(
                    color = if (highlightRight) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.primaryContainer,
                    shape = CircleShape,
                )
                .clickable { onOff() },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_off),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.tertiary,
                modifier = Modifier.size(BigIconsSize44)
            )
        }

        // Alarm
        Box(
            modifier = Modifier
                .size(LargeIconsSize78)
                .align(Alignment.Center)
                .offset { IntOffset(animatableOffsetX.value.roundToInt(), 0) }
                .background(
                    color = MaterialTheme.colorScheme.primary,
                    shape = CircleShape,
                )
                .pointerInput(Unit) {
                    detectDragGestures(
                        onDragStart = {
                            highlightLeft = false
                            highlightRight = false
                        },
                        onDrag = { change, dragAmount ->
                            change.consume()

                            val leftLimit = -offsetSidePx - LargeIconsSize134.toPx() / 2
                            val rightLimit = offsetSidePx + LargeIconsSize134.toPx() / 2

                            val newOffset = (animatableOffsetX.value + dragAmount.x)
                                .coerceIn(leftLimit, rightLimit)

                            coroutineScope.launch {
                                animatableOffsetX.snapTo(newOffset)
                            }

                            highlightLeft = newOffset <= leftLimit / 2
                            highlightRight = newOffset >= rightLimit / 2
                        },
                        onDragEnd = {
                            if (highlightLeft) {
                                onPostponeClick()
                            } else if (highlightRight) {
                                onOff()
                            }

                            coroutineScope.launch {
                                animatableOffsetX.animateTo(0f, animationSpec = tween(durationMillis = 300))
                                highlightLeft = false
                                highlightRight = false
                            }
                        },
                        onDragCancel = {
                            coroutineScope.launch {
                                animatableOffsetX.animateTo(0f, animationSpec = tween(durationMillis = 300))
                                highlightLeft = false
                                highlightRight = false
                            }
                        }
                    )
                },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_alarm),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.size(BigIconsSize44)
            )
        }
    }
}

@Preview
@Composable
fun AlarmScreenDuringAlarmPreview(){
    Theme {
        AlarmOffPostpone(onPostponeClick = {}, onOff = {})
    }
}