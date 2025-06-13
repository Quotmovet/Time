package com.example.time.presentation.components.alarmscreen.additional

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import com.example.time.presentation.common.Dimens.BigIconsSize44

@Composable
fun WeekDays(letter: String) {
    var selected by remember { mutableStateOf(false) }

    val backgroundColor by animateColorAsState(
        targetValue = if (selected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onPrimaryContainer,
        animationSpec = tween(500, easing = FastOutSlowInEasing),
        label = "BackgroundColor"
    )

    val textColor by animateColorAsState(
        targetValue = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onPrimary,
        animationSpec = tween(500, easing = FastOutSlowInEasing),
        label = "TextColor"
    )

    IconButton(
        onClick = { selected = !selected },
        modifier = Modifier
            .size(BigIconsSize44)
            .clip(CircleShape)
            .background(backgroundColor)
    ) {
        Text(
            text = letter,
            color = textColor,
            style = MaterialTheme.typography.titleMedium
        )
    }
}

@Preview
@Composable
fun WeekDaysPreview(){
    WeekDays("M")
}