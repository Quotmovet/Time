package com.example.time.presentation.components.alarmscreen.alarmScreenDuringAlarm

import androidx.compose.animation.animateColorAsState
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
import com.example.time.R
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.EaseInOutSine
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import com.example.time.presentation.common.Dimens.BigIconsSize44
import com.example.time.presentation.common.Dimens.LargeIconsSize134
import com.example.time.presentation.common.Dimens.LargeIconsSize78
import com.example.time.presentation.common.Dimens.Offset30
import kotlin.math.roundToInt
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import com.example.time.presentation.common.Dimens.SmallPadding8
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
    var isDragging by remember { mutableStateOf(false) }
    var isPostponeTriggered by remember { mutableStateOf(false) }
    var isOffTriggered by remember { mutableStateOf(false) }

    val coroutineScope = rememberCoroutineScope()

    val leftBackgroundColor by animateColorAsState(
        targetValue = if (highlightLeft) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.primaryContainer,
        animationSpec = tween(durationMillis = 200),
        label = "leftBackgroundColor"
    )

    val rightBackgroundColor by animateColorAsState(
        targetValue = if (highlightRight) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.primaryContainer,
        animationSpec = tween(durationMillis = 200),
        label = "rightBackgroundColor"
    )

    val leftIconColor by animateColorAsState(
        targetValue = if (highlightLeft) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.tertiary,
        animationSpec = tween(durationMillis = 200),
        label = "leftIconColor"
    )

    val rightIconColor by animateColorAsState(
        targetValue = if (highlightRight) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.tertiary,
        animationSpec = tween(durationMillis = 200),
        label = "rightIconColor"
    )

    val leftTextColor by animateColorAsState(
        targetValue = if (highlightLeft) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.tertiary,
        animationSpec = tween(durationMillis = 200),
        label = "leftTextColor"
    )

    val rightTextColor by animateColorAsState(
        targetValue = if (highlightRight) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.tertiary,
        animationSpec = tween(durationMillis = 200),
        label = "rightTextColor"
    )

    val alarmScale by animateFloatAsState(
        targetValue = if (isDragging) 1.1f else 1f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "alarmScale"
    )

    val infiniteTransition = rememberInfiniteTransition(label = "pulseTransition")
    val pulseScale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.05f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1500, easing = EaseInOutSine),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulseScale"
    )

    val rotationAngle by infiniteTransition.animateFloat(
        initialValue = -5f,
        targetValue = 5f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 120, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "rotationAngle"
    )

    val leftScale by animateFloatAsState(
        targetValue = when {
            isPostponeTriggered -> 15f
            highlightLeft -> 1.15f
            else -> 1f
        },
        animationSpec = if (isPostponeTriggered) {
            spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessLow
            )
        } else {
            spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessHigh
            )
        },
        label = "leftScale"
    )

    val rightScale by animateFloatAsState(
        targetValue = when {
            isOffTriggered -> 15f
            highlightRight -> 1.15f
            else -> 1f
        },
        animationSpec = if (isOffTriggered) {
            spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessLow
            )
        } else {
            spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessHigh
            )
        },
        label = "rightScale"
    )

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
                .scale(leftScale)
                .background(
                    color = leftBackgroundColor,
                    shape = CircleShape,
                )
                .clickable {
                    coroutineScope.launch {
                        isPostponeTriggered = true
                        kotlinx.coroutines.delay(400)
                        onPostponeClick()
                    }
                },
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Spacer(modifier = Modifier.height(SmallPadding8))
                Icon(
                    painter = painterResource(id = R.drawable.ic_postpone),
                    contentDescription = null,
                    tint = leftIconColor,
                    modifier = Modifier
                        .size(BigIconsSize44)
                        .rotate(if (highlightLeft) 10f else 0f)
                )
                Spacer(modifier = Modifier.height(SmallPadding8))
                Text(
                    text = stringResource(R.string.postpone),
                    style = MaterialTheme.typography.bodyMedium,
                    color = leftTextColor
                )
            }
        }

        // Off
        Box(
            modifier = Modifier
                .size(LargeIconsSize134)
                .align(Alignment.CenterEnd)
                .offset { IntOffset(x = offsetSidePx.roundToInt(), y = 0) }
                .scale(rightScale)
                .background(
                    color = rightBackgroundColor,
                    shape = CircleShape,
                )
                .clickable {
                    coroutineScope.launch {
                        isOffTriggered = true
                        kotlinx.coroutines.delay(400)
                        onOff()
                    }
                },
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Spacer(modifier = Modifier.height(SmallPadding8))
                Icon(
                    painter = painterResource(id = R.drawable.ic_off),
                    contentDescription = null,
                    tint = rightIconColor,
                    modifier = Modifier
                        .size(BigIconsSize44)
                        .rotate(if (highlightRight) -10f else 0f)
                )
                Spacer(modifier = Modifier.height(SmallPadding8))
                Text(
                    text = stringResource(R.string.cancel),
                    style = MaterialTheme.typography.bodyMedium,
                    color = rightTextColor
                )
            }
        }

        // Alarm
        Box(
            modifier = Modifier
                .size(LargeIconsSize78)
                .align(Alignment.Center)
                .offset { IntOffset(animatableOffsetX.value.roundToInt(), 0) }
                .scale(if (isDragging) alarmScale else pulseScale)
                .background(
                    color = MaterialTheme.colorScheme.primary,
                    shape = CircleShape,
                )
                .pointerInput(Unit) {
                    detectDragGestures(
                        onDragStart = {
                            isDragging = true
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
                            isDragging = false

                            if (highlightLeft) {
                                onPostponeClick()
                            } else if (highlightRight) {
                                onOff()
                            }

                            coroutineScope.launch {
                                animatableOffsetX.animateTo(
                                    targetValue = 0f,
                                    animationSpec = spring(
                                        dampingRatio = Spring.DampingRatioMediumBouncy,
                                        stiffness = Spring.StiffnessMedium
                                    )
                                )
                                highlightLeft = false
                                highlightRight = false
                            }
                        },
                        onDragCancel = {
                            isDragging = false

                            coroutineScope.launch {
                                animatableOffsetX.animateTo(
                                    targetValue = 0f,
                                    animationSpec = spring(
                                        dampingRatio = Spring.DampingRatioMediumBouncy,
                                        stiffness = Spring.StiffnessMedium
                                    )
                                )
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
                modifier = Modifier
                    .size(BigIconsSize44)
                    .rotate(if (isDragging) 0f else rotationAngle)
            )
        }
    }
}