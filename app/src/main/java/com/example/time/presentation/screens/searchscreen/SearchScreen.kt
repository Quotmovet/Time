package com.example.time.presentation.screens.searchscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.time.presentation.common.theme.Theme
import com.example.time.presentation.components.searchscreen.ChosenSearchItem
import com.example.time.presentation.components.searchscreen.PlaceholderSearchScreen
import com.example.time.presentation.components.searchscreen.SearchField
import com.example.time.presentation.components.searchscreen.SearchItem

@Composable
fun SearchScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column {
            SearchField()
            SearchItem()
            ChosenSearchItem()
            PlaceholderSearchScreen()
        }
    }
}

@Preview
@Composable
fun PreviewSearchScreen() {
    Theme {
        SearchScreen()
    }
}