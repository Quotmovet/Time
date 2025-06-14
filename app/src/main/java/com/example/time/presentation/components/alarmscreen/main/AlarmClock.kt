package com.example.time.presentation.components.alarmscreen.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimeInput
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerDefaults
import androidx.compose.material3.TimePickerState
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.example.time.R
import com.example.time.presentation.common.Dimens.LargePadding30
import com.example.time.presentation.common.Dimens.SmallPadding4
import com.example.time.presentation.common.Dimens.TextSize48
import com.example.time.presentation.common.theme.Geist

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlarmClock(
    timeState: TimePickerState,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    var showKeyboardInput by remember { mutableStateOf(false) }

    val keyboard = painterResource(R.drawable.ic_keyboard)
    val clock = painterResource(R.drawable.ic_world_time)

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = stringResource(R.string.choose_time),
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onBackground
            )
        },
        text = {
            CompositionLocalProvider(
                LocalTextStyle provides TextStyle(
                    fontFamily = Geist,
                    fontWeight = FontWeight.Bold,
                    fontSize = TextSize48
                )
            ) {
                if (showKeyboardInput) {
                    TimeInput(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = LargePadding30),
                        state = timeState,
                        colors = TimePickerDefaults.colors(
                            containerColor = MaterialTheme.colorScheme.background,
                            clockDialColor = MaterialTheme.colorScheme.primaryContainer,
                            timeSelectorSelectedContainerColor = MaterialTheme.colorScheme.tertiary,
                            timeSelectorUnselectedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                            timeSelectorSelectedContentColor = MaterialTheme.colorScheme.primaryContainer,
                            timeSelectorUnselectedContentColor = MaterialTheme.colorScheme.primary,
                            periodSelectorBorderColor = MaterialTheme.colorScheme.primary
                        )
                    )
                } else {
                    TimePicker(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = SmallPadding4),
                        state = timeState,
                        colors = TimePickerDefaults.colors(
                            containerColor = MaterialTheme.colorScheme.background,
                            clockDialColor = MaterialTheme.colorScheme.primaryContainer,
                            timeSelectorSelectedContainerColor = MaterialTheme.colorScheme.tertiary,
                            timeSelectorUnselectedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                            timeSelectorSelectedContentColor = MaterialTheme.colorScheme.primaryContainer,
                            timeSelectorUnselectedContentColor = MaterialTheme.colorScheme.primary,
                            periodSelectorBorderColor = MaterialTheme.colorScheme.primary
                        )
                    )
                }
            }
        },
        confirmButton = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()
            ) {
                IconButton(onClick = {
                    showKeyboardInput = !showKeyboardInput
                }) {
                    Icon(
                        painter = if (showKeyboardInput) clock else keyboard,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                TextButton(onClick = onConfirm) {
                    Text(
                        text = stringResource(R.string.ok),
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
                TextButton(onClick = onDismiss) {
                    Text(
                        text = stringResource(R.string.cancel),
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
        },
        containerColor = MaterialTheme.colorScheme.background,
        textContentColor = MaterialTheme.colorScheme.onBackground,
        iconContentColor = MaterialTheme.colorScheme.onBackground,
    )
}
