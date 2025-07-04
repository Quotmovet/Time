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
import androidx.compose.foundation.layout.BoxScope
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

@Composable
fun AlarmScreen(viewModel: AlarmScreenViewModel = hiltViewModel()) {
    val alarmState by viewModel.alarmState.collectAsState()
    var showDialog by remember { mutableStateOf(false) }
    var selectedAlarm by remember { mutableStateOf<AlarmModel?>(null) }
    var expandedAlarmId by remember { mutableStateOf<Int?>(null) }
    val context = LocalContext.current

    @Suppress("DEPRECATION")
    val launcher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.StartActivityForResult(),
        ) { result ->
            val uri = result.data?.getParcelableExtra<Uri>(RingtoneManager.EXTRA_RINGTONE_PICKED_URI)
            selectedAlarm?.let { alarm ->
                viewModel.updateAlarmSound(alarm, uri)
                selectedAlarm = null
            }
        }

    val intent =
        remember {
            Intent(RingtoneManager.ACTION_RINGTONE_PICKER).apply {
                putExtra(RingtoneManager.EXTRA_RINGTONE_TYPE, RingtoneManager.TYPE_ALARM)
                putExtra(RingtoneManager.EXTRA_RINGTONE_TITLE, R.string.select_alarm_sound)
                putExtra(RingtoneManager.EXTRA_RINGTONE_SHOW_SILENT, false)
                putExtra(RingtoneManager.EXTRA_RINGTONE_SHOW_DEFAULT, true)
            }
        }

    val shouldShowOverlay = alarmState.size > 3

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        AlarmList(
            alarms = alarmState,
            expandedAlarmId = expandedAlarmId,
            onExpandToggle = { id -> expandedAlarmId = if (expandedAlarmId == id) null else id },
            onCheckedChange = { alarm, isChecked ->
                viewModel.updateAlarmActivation(alarm, isChecked)
                if (isChecked) Toast.makeText(context, R.string.alarm_is_activate, Toast.LENGTH_SHORT).show()
            },
            onDayToggle = { alarm, day -> viewModel.updateAlarmDays(alarm, day) },
            onNameChange = { alarm, name -> viewModel.updateAlarmName(alarm, name) },
            onSoundChange = { alarm ->
                selectedAlarm = alarm
                launcher.launch(intent)
            },
            onVibrationChange = { alarm, vib -> viewModel.updateAlarmVibration(alarm, vib) },
            onDelete = { alarm ->
                viewModel.deleteAlarm(alarm)
                if (expandedAlarmId == alarm.id) expandedAlarmId = null
                Toast.makeText(context, R.string.alarm_is_delete, Toast.LENGTH_SHORT).show()
            },
        )

        AnimatedOverlay(shouldShowOverlay)

        AddButtonAlarmScreen(
            modifier =
                Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = MediumPadding24)
                    .size(LargeIconsSize64),
            onClick = { showDialog = true },
        )

        if (showDialog) {
            CreateAlarmDialog(
                onDismiss = { showDialog = false },
                onConfirm = { hour, minute ->
                    showDialog = false
                    viewModel.createAlarm(hour, minute)
                },
            )
        }
    }
}

@Composable
fun AlarmList(
    alarms: List<AlarmModel>,
    expandedAlarmId: Int?,
    onExpandToggle: (Int) -> Unit,
    onCheckedChange: (AlarmModel, Boolean) -> Unit,
    onDayToggle: (AlarmModel, Int) -> Unit,
    onNameChange: (AlarmModel, String) -> Unit,
    onSoundChange: (AlarmModel) -> Unit,
    onVibrationChange: (AlarmModel, Boolean) -> Unit,
    onDelete: (AlarmModel) -> Unit,
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(top = LargePadding60, bottom = 120.dp),
        verticalArrangement = Arrangement.spacedBy(MediumPadding16),
    ) {
        items(alarms, key = { it.id }) { alarm ->
            AlarmItem(
                modifier = Modifier.fillMaxWidth(),
                id = alarm.id,
                time = "${alarm.hour}:${alarm.minute.toString().padStart(2, '0')}",
                days = alarm.days,
                name = alarm.name,
                isActivated = alarm.isActivated,
                isVibration = alarm.isVibration,
                isExpanded = expandedAlarmId == alarm.id,
                selectedDays = alarm.days.split(",").mapNotNull { it.toIntOrNull() }.toSet(),
                onExpandToggle = { onExpandToggle(alarm.id) },
                onCheckedChange = { onCheckedChange(alarm, it) },
                onDayToggle = { onDayToggle(alarm, it) },
                onNameChange = { onNameChange(alarm, it) },
                onSoundChange = { onSoundChange(alarm) },
                onVibrationChange = { onVibrationChange(alarm, it) },
                onDelete = { onDelete(alarm) },
            )
        }
    }
}

@Composable
fun BoxScope.AnimatedOverlay(visible: Boolean) {
    val animatedHeight by animateFloatAsState(
        targetValue = if (visible) 80f else 0f,
        animationSpec = tween(800, easing = FastOutSlowInEasing),
        label = "overlay_height",
    )
    val animatedAlpha by animateFloatAsState(
        targetValue = if (visible) 1f else 0f,
        animationSpec = tween(800, easing = FastOutSlowInEasing),
        label = "overlay_alpha",
    )

    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(tween(400)) + slideInVertically { it },
        exit = fadeOut(tween(400)) + slideOutVertically { it },
        modifier =
            Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth(),
    ) {
        Box(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .height(animatedHeight.dp)
                    .background(
                        brush =
                            Brush.verticalGradient(
                                colors =
                                    listOf(
                                        Color.Transparent,
                                        Color.Black.copy(alpha = 0.1f * animatedAlpha),
                                        Color.Black.copy(alpha = 0.3f * animatedAlpha),
                                    ),
                            ),
                    ),
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateAlarmDialog(
    onDismiss: () -> Unit,
    onConfirm: (Int, Int) -> Unit,
) {
    val timeState =
        rememberTimePickerState(
            initialHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY),
            initialMinute = Calendar.getInstance().get(Calendar.MINUTE),
            is24Hour = true,
        )
    AlarmClock(
        timeState = timeState,
        onDismiss = onDismiss,
        onConfirm = {
            onConfirm(timeState.hour, timeState.minute)
        },
    )
}
