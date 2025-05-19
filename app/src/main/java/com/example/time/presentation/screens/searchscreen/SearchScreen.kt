package com.example.time.presentation.screens.searchscreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.time.presentation.common.Dimens.LargePadding90
import com.example.time.presentation.components.searchscreen.PlaceholderSearchScreen
import com.example.time.presentation.components.searchscreen.SearchField

@Composable
fun SearchScreen() {

    var searchText by remember { mutableStateOf("") }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column {

            SearchField(
                text = searchText,
                onTextChange = { searchText = it }
            )

            Spacer(modifier = Modifier.height(LargePadding90))
            AnimatedVisibility(visible = searchText.isEmpty()) {
                PlaceholderSearchScreen()
            }
        }
    }
}
