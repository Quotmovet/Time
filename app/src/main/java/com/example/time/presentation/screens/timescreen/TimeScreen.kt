@file:OptIn(ExperimentalMaterialApi::class)

package com.example.time.presentation.screens.timescreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.DismissDirection
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.rememberDismissState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.time.presentation.common.Dimens.LargeIconsSize64
import com.example.time.presentation.common.Dimens.LargePadding34
import com.example.time.presentation.common.Dimens.LargePadding45
import com.example.time.presentation.common.Dimens.LargePadding80
import com.example.time.presentation.common.Dimens.MainSize
import com.example.time.presentation.common.Dimens.MediumPadding24
import com.example.time.presentation.common.Dimens.MediumPadding16
import com.example.time.presentation.common.Dimens.MediumPadding20
import com.example.time.presentation.common.Dimens.MediumPadding22
import com.example.time.presentation.common.Dimens.PrimaryCorner
import com.example.time.presentation.common.util.formater.formatOffset
import com.example.time.presentation.components.timescreen.AddButton
import com.example.time.presentation.components.timescreen.CitiesTimeItem
import com.example.time.presentation.components.timescreen.MainClock
import com.example.time.presentation.viewmodel.timescreen.TimeScreenViewModel

@Composable
fun TimeScreen(
    navController: NavController,
    viewModel: TimeScreenViewModel = hiltViewModel()
) {

    val currentTime by viewModel.currentTime
    val currentDate by viewModel.currentDate

    val selectedTimeZones by viewModel.selectedTimeState.collectAsState()

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = LargePadding80)
        ) {
            item {
                Spacer(modifier = Modifier.height(LargePadding34))
                MainClock(currentTime, currentDate)
                Spacer(modifier = Modifier.height(LargePadding45))
            }

            items(selectedTimeZones.size) { timeZoneModel ->
                val timezoneData = selectedTimeZones[timeZoneModel]

                val dismissState = rememberDismissState(
                    confirmStateChange = {
                        if (it == DismissValue.DismissedToStart) {
                            viewModel.deleteSelectedTimezone(timezoneData.timeZone)
                            true
                        } else false
                    }
                )

                SwipeToDismiss(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = MediumPadding22),
                    state = dismissState,
                    directions = setOf(DismissDirection.EndToStart),
                    background = {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .height(MainSize)
                                .background(
                                    color =  MaterialTheme.colorScheme.error,
                                    shape = RoundedCornerShape(PrimaryCorner)
                                )
                                .padding(horizontal = MediumPadding20),
                            contentAlignment = Alignment.CenterEnd
                        ) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Delete",
                                tint = MaterialTheme.colorScheme.onError
                            )
                        }
                    },
                    dismissContent = {
                        CitiesTimeItem(
                            cityName = timezoneData.cityName,
                            time = timezoneData.time,
                            offset = formatOffset(timezoneData.offset)
                        )
                    }
                )

                Spacer(modifier = Modifier.height(MediumPadding16))
            }
        }

        AddButton(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = MediumPadding24)
                .size(LargeIconsSize64),
            navController
        )
    }
}