package com.example.time.presentation.components.alarmscreen.additional

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.example.time.R
import com.example.time.presentation.common.Dimens.PrimaryIconsSize
import com.example.time.presentation.common.Dimens.PrimaryWidth

@Composable
fun CheckRadioButton(
    modifier: Modifier,
    selected: Boolean,
    onClick: () -> Unit,
) {
    val activeColor = MaterialTheme.colorScheme.primary

    val backgroundColor by animateColorAsState(
        targetValue = Color.Transparent,
        animationSpec = tween(300),
        label = "Background",
    )

    val borderModifier =
        if (!selected) {
            Modifier.border(PrimaryWidth, activeColor, CircleShape)
        } else {
            Modifier
        }

    Box(
        modifier =
            Modifier
                .size(PrimaryIconsSize)
                .clip(CircleShape)
                .then(borderModifier)
                .background(backgroundColor)
                .clickable { onClick() },
        contentAlignment = Alignment.Center,
    ) {
        AnimatedVisibility(
            visible = selected,
            enter = fadeIn() + scaleIn(),
            exit = fadeOut(animationSpec = tween(300)) + scaleOut(animationSpec = tween(300)),
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_checkmark),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(PrimaryIconsSize),
            )
        }
    }
}
