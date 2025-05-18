package com.example.time.presentation.screens.timescreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.time.presentation.common.Dimens.LargeIconsSize64
import com.example.time.presentation.common.Dimens.LargePadding34
import com.example.time.presentation.common.Dimens.LargePadding45
import com.example.time.presentation.common.Dimens.LargePadding80
import com.example.time.presentation.common.Dimens.MediumPadding24
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

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(bottom = LargePadding80)
        ) {
            Spacer(modifier = Modifier.height(LargePadding34))
            MainClock(currentTime, currentDate)
            Spacer(modifier = Modifier.height(LargePadding45))
            CitiesTimeItem()
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