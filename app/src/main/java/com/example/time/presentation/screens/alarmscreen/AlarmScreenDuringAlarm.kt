package com.example.time.presentation.screens.alarmscreen

import android.app.Activity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.time.R
import com.example.time.presentation.components.alarmscreen.alarmScreenDuringAlarm.AlarmInformationOnDuring
import com.example.time.presentation.components.alarmscreen.alarmScreenDuringAlarm.AlarmOffPostpone
import com.example.time.presentation.viewmodel.alarmscreen.AlarmViewModelDuringAlarm

@Composable
fun AlarmScreenDuringAlarm(
    alarmId: Int,
    viewModel: AlarmViewModelDuringAlarm = hiltViewModel(),
) {
    val alarm by viewModel.alarm
    val currentDate by viewModel.currentDate
    val currentTime by viewModel.currentTime

    val context = LocalContext.current
    val activity = context as? Activity

    LaunchedEffect(alarmId) {
        viewModel.getAlarmById(alarmId)
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        Spacer(modifier = Modifier.height(150.dp))

        if (alarm != null) {
            AlarmInformationOnDuring(
                alarmName = alarm?.name ?: stringResource(R.string.alarm),
                alarmTime = currentTime,
                alarmDate = currentDate
            )

            Spacer(modifier = Modifier.height(215.dp))

            AlarmOffPostpone(
                onPostponeClick = {
                    viewModel.snoozeToday()
                    activity?.finish()
                },
                onOff = {
                    viewModel.dismissToday()
                    activity?.finish()
                }
            )
        }
    }
}
