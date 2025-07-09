package com.example.time.presentation.ui.components.stopwatchscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.time.R
import com.example.time.presentation.ui.common.Dimens.LargeIconsSize80
import com.example.time.presentation.ui.common.Dimens.MediumPadding24
import com.example.time.presentation.ui.common.theme.Theme

@Composable
fun BottomButtons(
    onStartClick: () -> Unit,
    onPauseClick: () -> Unit,
    onResetClick: () -> Unit,
    onSplitTime: () -> Unit,
    isRunning: Boolean = true
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {

        if(!isRunning) {
            IconButton(
                onClick = onStartClick
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_start_button),
                    contentDescription = "Start",
                    tint = MaterialTheme.colorScheme.onBackground,
                    modifier =
                        Modifier
                            .padding(horizontal = MediumPadding24)
                            .size(LargeIconsSize80),
                )
            }
        } else {
            IconButton(
                onClick = onPauseClick
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_pause_button),
                    contentDescription = "Pause",
                    tint = MaterialTheme.colorScheme.onBackground,
                    modifier =
                        Modifier
                            .padding(horizontal = MediumPadding24)
                            .size(LargeIconsSize80),
                )
            }
        }

        IconButton(
            onClick = onResetClick
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_reset_button),
                contentDescription = "Reset",
                tint = MaterialTheme.colorScheme.onBackground,
                modifier =
                    Modifier
                        .size(LargeIconsSize80),
            )
        }

        IconButton(
            onClick = onSplitTime
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_split_time_button),
                contentDescription = "Split",
                tint = MaterialTheme.colorScheme.onBackground,
                modifier =
                    Modifier
                        .size(LargeIconsSize80),
            )
        }
    }
}

@Preview
@Composable
fun BottomButtonsPreview() {
    Theme {
        BottomButtons(
            onStartClick = {},
            onPauseClick = {},
            onResetClick = {},
            onSplitTime = {}
        )
    }
}
