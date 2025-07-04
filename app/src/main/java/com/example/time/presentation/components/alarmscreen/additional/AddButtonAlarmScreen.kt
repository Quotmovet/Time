package com.example.time.presentation.components.alarmscreen.additional

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.time.R
import com.example.time.presentation.common.Dimens.LargeIconsSize118
import com.example.time.presentation.common.Dimens.SmallPadding4

@Composable
fun AddButtonAlarmScreen(
    modifier: Modifier,
    onClick: () -> Unit,
) {
    IconButton(
        onClick = onClick,
        modifier = modifier,
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_add_button),
            modifier =
                Modifier
                    .padding(SmallPadding4)
                    .size(LargeIconsSize118),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onBackground,
        )
    }
}
