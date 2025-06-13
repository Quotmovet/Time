package com.example.time.presentation.screens.alarmscreen

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.time.presentation.common.Dimens.LargeIconsSize64
import com.example.time.presentation.common.Dimens.LargePadding60
import com.example.time.presentation.common.Dimens.MediumPadding24
import com.example.time.presentation.components.alarmscreen.additional.AddButtonAlarmScreen
import com.example.time.presentation.components.alarmscreen.main.AlarmClock
import com.example.time.presentation.components.alarmscreen.main.AlarmItem
import com.example.time.presentation.viewmodel.alarmscreen.AlarmScreenViewModel

@Composable
fun AlarmScreen(
    navController: NavController,
    viewModel: AlarmScreenViewModel = hiltViewModel()
) {

    var showDialog by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        // Состояние для текущего количества элементов
        val itemCount = 4
        val shouldShowOverlay = itemCount > 4

        // Анимация высоты фона
        val animatedHeight by animateFloatAsState(
            targetValue = if (shouldShowOverlay) 80f else 0f,
            animationSpec = tween(
                durationMillis = 600,
                easing = FastOutSlowInEasing
            )
        )

        // Анимация прозрачности фона
        val animatedAlpha by animateFloatAsState(
            targetValue = if (shouldShowOverlay) 1f else 0f,
            animationSpec = tween(
                durationMillis = 600,
                easing = FastOutSlowInEasing
            )
        )

        Spacer(modifier = Modifier.padding(top = LargePadding60))

        AlarmItem(
            time = "8:30",
            days = "Mon, Tue, Wed, Thu, Fri, Sat, Sun",
            name = "Alarm",
            isChecked = true,
            onCheckedChange = {},
            onClick = {}
            )

        // Затемнение снизу
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(animatedHeight.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black.copy(alpha = 0.1f * animatedAlpha),
                            Color.Black.copy(alpha = 0.3f * animatedAlpha)
                        ),
                        startY = 0f,
                        endY = Float.POSITIVE_INFINITY
                    )
                )
        )

        AddButtonAlarmScreen(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = MediumPadding24)
                .size(LargeIconsSize64),
            onClick = { showDialog = true }
        )

        if (showDialog) {
            AlarmClock(
                onDismiss = { showDialog = false },
                onConfirm = { h, m ->
                    showDialog = false
                }
            )
        }
    }
}