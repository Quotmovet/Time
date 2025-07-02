package com.example.time.presentation.screens.alarmscreen

import android.content.Intent
import android.media.RingtoneManager
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.time.R
import com.example.time.domain.model.alarmscreen.AlarmModel
import com.example.time.presentation.common.Dimens.LargeIconsSize64
import com.example.time.presentation.common.Dimens.LargePadding60
import com.example.time.presentation.common.Dimens.MediumPadding16
import com.example.time.presentation.common.Dimens.MediumPadding24
import com.example.time.presentation.components.alarmscreen.additional.AddButtonAlarmScreen
import com.example.time.presentation.components.alarmscreen.main.AlarmClock
import com.example.time.presentation.components.alarmscreen.main.AlarmItem
import com.example.time.presentation.viewmodel.alarmscreen.AlarmScreenViewModel
import java.util.Calendar

@Suppress("DEPRECATION")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlarmScreen(
    viewModel: AlarmScreenViewModel = hiltViewModel()
) {
    val alarmState by viewModel.alarmState.collectAsState()
    var showDialog by remember { mutableStateOf(false) }
    var selectedAlarm by remember { mutableStateOf<AlarmModel?>(null) }
    var expandedAlarmId by remember { mutableStateOf<Int?>(null) }
    val context = LocalContext.current

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        val uri = result.data?.getParcelableExtra<Uri>(RingtoneManager.EXTRA_RINGTONE_PICKED_URI)
        selectedAlarm?.let { alarm ->
            viewModel.updateAlarmSound(alarm, uri)
            selectedAlarm = null
        }
    }

    val intent = remember {
        Intent(RingtoneManager.ACTION_RINGTONE_PICKER).apply {
            putExtra(RingtoneManager.EXTRA_RINGTONE_TYPE, RingtoneManager.TYPE_ALARM)
            putExtra(RingtoneManager.EXTRA_RINGTONE_TITLE, R.string.select_alarm_sound)
            putExtra(RingtoneManager.EXTRA_RINGTONE_SHOW_SILENT, false)
            putExtra(RingtoneManager.EXTRA_RINGTONE_SHOW_DEFAULT, true)
        }
    }

    val shouldShowOverlay = alarmState.size > 3

    val animatedHeight by animateFloatAsState(
        targetValue = if (shouldShowOverlay) 80f else 0f,
        animationSpec = tween(800, easing = FastOutSlowInEasing),
        label = "overlay_height"
    )

    val animatedAlpha by animateFloatAsState(
        targetValue = if (shouldShowOverlay) 1f else 0f,
        animationSpec = tween(800, easing = FastOutSlowInEasing),
        label = "overlay_alpha"
    )

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                top = LargePadding60,
                bottom = 120.dp
            ),
            verticalArrangement = Arrangement.spacedBy(MediumPadding16)
        ) {
            items(
                items = alarmState,
                key = { alarm -> alarm.id }
            ) { alarm ->
                AlarmItem(
                    modifier = Modifier.fillMaxWidth(),
                    id = alarm.id,
                    time = "${alarm.hour}:${alarm.minute.toString().padStart(2, '0')}",
                    days = alarm.days,
                    name = alarm.name,
                    isActivated = alarm.isActivated,
                    isVibration = alarm.isVibration,
                    isExpanded = expandedAlarmId == alarm.id,
                    selectedDays = alarm.days.split(",")
                        .mapNotNull { it.toIntOrNull() }
                        .toSet(),

                    onExpandToggle = {
                        expandedAlarmId = if (expandedAlarmId == alarm.id) null else alarm.id
                    },

                    onCheckedChange = { isChecked ->
                        viewModel.updateAlarmActivation(alarm, isChecked)
                        if (isChecked) Toast.makeText(context, R.string.alarm_is_activate, Toast.LENGTH_SHORT).show()
                    },

                    onDayToggle = { day ->
                        viewModel.updateAlarmDays(alarm, day)
                    },

                    onNameChange = { newName ->
                        viewModel.updateAlarmName(alarm, newName)
                    },

                    onSoundChange = { id ->
                        selectedAlarm = alarm
                        launcher.launch(intent)
                    },

                    onVibrationChange = { isVibration ->
                        viewModel.updateAlarmVibration(alarm, isVibration)
                    },

                    onDelete = {
                        viewModel.deleteAlarm(alarm)
                        if (expandedAlarmId == alarm.id) {
                            expandedAlarmId = null
                        }
                        Toast.makeText(context, R.string.alarm_is_delete, Toast.LENGTH_SHORT).show()
                    }
                )
            }
        }

        AnimatedVisibility(
            visible = shouldShowOverlay,
            enter = fadeIn(
                animationSpec = tween(400, easing = FastOutSlowInEasing)
            ) + slideInVertically(
                initialOffsetY = { it },
                animationSpec = tween(400, easing = FastOutSlowInEasing)
            ),
            exit = fadeOut(
                animationSpec = tween(400, easing = FastOutSlowInEasing)
            ) + slideOutVertically(
                targetOffsetY = { it },
                animationSpec = tween(400, easing = FastOutSlowInEasing)
            ),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(animatedHeight.dp)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black.copy(alpha = 0.1f * animatedAlpha),
                                Color.Black.copy(alpha = 0.3f * animatedAlpha)
                            )
                        )
                    )
            )
        }

        AddButtonAlarmScreen(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = MediumPadding24)
                .size(LargeIconsSize64),
            onClick = { showDialog = true }
        )

        if (showDialog) {
            val timeState = rememberTimePickerState(
                initialHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY),
                initialMinute = Calendar.getInstance().get(Calendar.MINUTE),
                is24Hour = true
            )

            AlarmClock(
                timeState = timeState,
                onDismiss = { showDialog = false },
                onConfirm = {
                    showDialog = false
                    viewModel.createAlarm(timeState.hour, timeState.minute)
                }
            )
        }
    }
}