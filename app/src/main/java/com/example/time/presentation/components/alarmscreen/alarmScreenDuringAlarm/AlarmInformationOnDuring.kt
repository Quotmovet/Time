package com.example.time.presentation.components.alarmscreen.alarmScreenDuringAlarm

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.time.presentation.common.Dimens.MediumPadding16
import com.example.time.presentation.common.Dimens.MediumPadding20
import com.example.time.presentation.common.Dimens.MediumPadding22

@Composable
fun AlarmInformationOnDuring(
    alarmName: String,
    alarmTime: String,
    alarmDate: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = MediumPadding22),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(bottom = MediumPadding20),
            text = alarmName,
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onBackground
        )
        Text(
            modifier = Modifier.padding(bottom = MediumPadding16),
            text = alarmTime,
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
        Text(
            text = alarmDate,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}
