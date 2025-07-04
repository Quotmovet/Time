package com.example.time.presentation.components.timescreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.example.time.presentation.common.Dimens.LargeSize
import com.example.time.presentation.common.Dimens.MediumPadding22

@Composable
fun MainClock(
    currentTime: String,
    currentDate: String,
) {
    Box(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(horizontal = MediumPadding22)
                .height(LargeSize),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(
                modifier =
                    Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = currentTime,
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onBackground,
            )
            Text(
                modifier =
                    Modifier
                        .fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = currentDate,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onBackground,
            )
        }
    }
}
