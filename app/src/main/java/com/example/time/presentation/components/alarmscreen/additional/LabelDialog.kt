package com.example.time.presentation.components.alarmscreen.additional

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.window.Dialog
import com.example.time.R
import com.example.time.presentation.common.Dimens.BigIconsSize36
import com.example.time.presentation.common.Dimens.LargePadding30
import com.example.time.presentation.common.Dimens.MediumPadding16
import com.example.time.presentation.common.Dimens.MediumPadding24
import com.example.time.presentation.common.Dimens.PrimaryCorner
import com.example.time.presentation.common.Dimens.SmallPadding10
import com.example.time.presentation.common.Dimens.SmallPadding8

@Composable
fun LabelDialog(
    text: String,
    onTextChange: (String) -> Unit,
    onDismiss: () -> Unit,
    onConfirm: (label: String) -> Unit,
) {
    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(PrimaryCorner),
            color = MaterialTheme.colorScheme.background,
            tonalElevation = SmallPadding8,
        ) {
            Column(
                modifier =
                    Modifier
                        .padding(top = MediumPadding24, start = MediumPadding24, end = MediumPadding24, bottom = SmallPadding10)
                        .fillMaxWidth(),
            ) {
                OutlinedTextField(
                    value = text,
                    onValueChange = onTextChange,
                    label = {
                        Text(
                            text = stringResource(R.string.name),
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                        )
                    },
                    shape = RoundedCornerShape(PrimaryCorner),
                    singleLine = true,
                    trailingIcon = {
                        if (text.isNotEmpty()) {
                            IconButton(
                                onClick = {
                                    onTextChange("")
                                },
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Close,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.onBackground,
                                )
                            }
                        }
                    },
                    keyboardOptions =
                        KeyboardOptions(
                            imeAction = ImeAction.Done,
                        ),
                    keyboardActions =
                        KeyboardActions(
                            onDone = {
                                if (text.isNotBlank()) onConfirm(text)
                            },
                        ),
                )

                Spacer(modifier = Modifier.height(MediumPadding16))

                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .height(BigIconsSize36),
                ) {
                    TextButton(
                        onClick = onDismiss,
                        contentPadding = PaddingValues(start = LargePadding30),
                    ) {
                        Text(
                            text = stringResource(R.string.cancel),
                            style = MaterialTheme.typography.labelLarge,
                            color = MaterialTheme.colorScheme.onBackground,
                        )
                    }
                    TextButton(
                        onClick = { if (text.isNotBlank()) onConfirm(text) },
                        enabled = text.isNotBlank(),
                        contentPadding = PaddingValues(start = LargePadding30),
                    ) {
                        Text(
                            text = stringResource(R.string.ok),
                            style = MaterialTheme.typography.labelLarge,
                            color = MaterialTheme.colorScheme.onBackground,
                        )
                    }
                }
            }
        }
    }
}
