package com.example.time.presentation.components.alarmscreen.main

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.time.R
import com.example.time.presentation.common.Dimens.BigIconsSize34
import com.example.time.presentation.common.Dimens.MediumPadding16
import com.example.time.presentation.common.Dimens.MediumPadding22
import com.example.time.presentation.common.Dimens.MediumPadding24
import com.example.time.presentation.common.Dimens.PrimaryCorner
import com.example.time.presentation.common.Dimens.PrimaryIconsSize
import com.example.time.presentation.common.Dimens.SmallIconsSize16
import com.example.time.presentation.common.Dimens.SmallPadding10
import com.example.time.presentation.common.Dimens.SmallPadding12
import com.example.time.presentation.common.Dimens.SmallPadding4
import com.example.time.presentation.common.Dimens.SmallPadding6
import com.example.time.presentation.common.Dimens.SmallPadding8
import com.example.time.presentation.components.alarmscreen.additional.CheckRadioButton
import com.example.time.presentation.components.alarmscreen.additional.LabelDialog
import com.example.time.presentation.components.alarmscreen.additional.WeekDays

@Composable
fun AlarmItem(
    time: String,
    days: String,
    name: String,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    onClick: () -> Unit,
) {

    var expanded by remember { mutableStateOf(false) }
    var selected by remember { mutableStateOf(false) }
    val rotation by animateFloatAsState(targetValue = if (expanded) 180f else 0f, label = "")

    var showDialog by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = MediumPadding22)
            .clip(RoundedCornerShape(PrimaryCorner))
            .animateContentSize(),
        shape = RoundedCornerShape(PrimaryCorner),
        elevation = CardDefaults.cardElevation(SmallPadding4),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        onClick = {
            expanded = !expanded
            onClick()
        }
    ) {

        Column(modifier = Modifier.padding(all = SmallPadding10)) {

            if(!expanded) {
                Row(
                    modifier = Modifier.align(alignment = Alignment.End)
                ) {
                    Icon(
                        modifier = Modifier
                            .size(SmallIconsSize16)
                            .rotate(rotation),
                        painter = painterResource(R.drawable.ic_markdawn),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }

                Spacer(modifier = Modifier.height(SmallPadding10))
                TimeRow(time, days, isChecked, onCheckedChange)

            } else {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    verticalAlignment = Alignment.Top
                ) {
                    Icon(
                        modifier = Modifier.size(SmallIconsSize16),
                        painter = painterResource(R.drawable.ic_label),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onBackground
                    )

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(SmallIconsSize16)
                    ) {
                        TextButton(
                            onClick = { showDialog = true },
                            contentPadding = PaddingValues(2.dp),
                            modifier = Modifier.align(Alignment.CenterStart)
                        ) {
                            Text(
                                text = name,
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onBackground,
                                modifier = Modifier.align(alignment = Alignment.Top)
                            )
                        }
                    }

                    Icon(
                        modifier = Modifier
                            .size(SmallIconsSize16)
                            .rotate(rotation),
                        painter = painterResource(R.drawable.ic_markdawn),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onBackground
                    )

                    if (showDialog) {
                        LabelDialog(
                            text = name,
                            onTextChange = { name },
                            onDismiss = { showDialog = false },
                            onConfirm = { label ->
                                showDialog = false
                            }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(SmallPadding10))
                TimeRow(time, days, isChecked, onCheckedChange)

                Spacer(modifier = Modifier.padding(top = MediumPadding16))
                WeekDaysRow()

                Spacer(modifier = Modifier.height(MediumPadding24))
                AlarmActionsColumn(selected = selected, onToggleSelected = { selected = !selected })
            }
        }
    }
}

@Composable
private fun TimeRow(
    time: String,
    days: String,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(BigIconsSize34),
        verticalAlignment = Alignment.Bottom
    ) {
        Text(
            text = time,
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.width(SmallPadding6))
        Box(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = days,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.fillMaxWidth())
        }
        Switch(
            checked = isChecked,
            onCheckedChange = onCheckedChange,
            modifier = Modifier
                .size(BigIconsSize34)
                .padding(top = SmallPadding12)
                .scale(0.6f),
            colors = SwitchDefaults.colors(
                checkedThumbColor = MaterialTheme.colorScheme.onBackground,
                checkedTrackColor = MaterialTheme.colorScheme.primary,
                checkedBorderColor = Color.Transparent,
                uncheckedThumbColor = MaterialTheme.colorScheme.primary,
                uncheckedTrackColor = MaterialTheme.colorScheme.onBackground,
                uncheckedBorderColor = Color.Transparent
            )
        )
    }
}

@Composable
private fun WeekDaysRow() {
    val daysList = listOf(
        R.string.M, R.string.Tu, R.string.W,
        R.string.Th, R.string.F, R.string.Sa, R.string.Su
    )
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        daysList.forEachIndexed { index, resId ->
            WeekDays(stringResource(resId))
            if (index != daysList.lastIndex) {
                Spacer(modifier = Modifier.width(SmallPadding10))
            }
        }
    }
}

@Composable
private fun AlarmActionsColumn(selected: Boolean, onToggleSelected: (Boolean) -> Unit) {
    Column {
        Row {
            Icon(
                painter = painterResource(R.drawable.ic_bell),
                contentDescription = null,
                modifier = Modifier.size(PrimaryIconsSize),
                tint = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text = stringResource(R.string.sound),
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier
                    .padding(start = SmallPadding10)
                    .weight(1f)
                    .align(Alignment.CenterVertically)
            )
        }

        Spacer(modifier = Modifier.height(SmallPadding8))

        Row {
            Icon(
                painter = painterResource(R.drawable.ic_vibration),
                contentDescription = null,
                modifier = Modifier.size(PrimaryIconsSize),
                tint = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text = stringResource(R.string.vibration),
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier
                    .padding(start = SmallPadding10)
                    .weight(1f)
                    .align(Alignment.CenterVertically)
            )
            CheckRadioButton(
                selected = selected,
                onClick = { onToggleSelected(!selected) },
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }

        Spacer(modifier = Modifier.height(SmallPadding8))

        Row {
            Icon(
                painter = painterResource(R.drawable.ic_clean),
                contentDescription = null,
                modifier = Modifier.size(PrimaryIconsSize),
                tint = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text = stringResource(R.string.delete),
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier
                    .padding(start = SmallPadding10)
                    .weight(1f)
                    .align(Alignment.CenterVertically)
            )
        }
    }
}