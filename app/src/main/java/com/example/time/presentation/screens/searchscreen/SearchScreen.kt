package com.example.time.presentation.screens.searchscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.time.presentation.common.Dimens.LargePadding80
import com.example.time.presentation.common.Dimens.MediumPadding22
import com.example.time.presentation.components.searchscreen.PlaceholderSearchScreen
import com.example.time.presentation.components.searchscreen.SearchField
import com.example.time.presentation.components.searchscreen.SearchItem
import com.example.time.presentation.components.searchscreen.SearchItemPlaceholder
import com.example.time.presentation.viewmodel.searchscreen.SearchScreenState
import com.example.time.presentation.viewmodel.searchscreen.SearchScreenViewModel

@Composable
fun SearchScreen(
    searchScreenViewModel: SearchScreenViewModel = hiltViewModel()
) {

    var searchText by remember { mutableStateOf("") }

    val searchState by searchScreenViewModel.searchState.collectAsState(
        SearchScreenState(
            timeZone = emptyList(),
            isLoading = false,
            isFailed = null,
            isEmpty = false
        )
    )

    LaunchedEffect(searchText) {
        if (searchText.isNotEmpty()) {
            searchScreenViewModel.searchDebouncer(searchText)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = LargePadding80)
    ) {

        SearchField(
            text = searchText,
            onTextChange = { searchText = it },
            onSearch = { searchScreenViewModel.searchDebouncer(searchText) }
        )

        Column {

            when {
                searchText.isEmpty() -> {
                    Spacer(modifier = Modifier.height(LargePadding80))
                    PlaceholderSearchScreen()
                }

                searchState.isLoading -> {
                    Spacer(modifier = Modifier.height(LargePadding80))
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                searchState.isFailed != null -> {
                    Spacer(modifier = Modifier.height(LargePadding80))
                    Text("Ошибка: ${searchState.isFailed}")
                    LazyColumn {
                        items(5) {
                            SearchItemPlaceholder()
                        }
                    }
                }

                searchState.timeZone.isNotEmpty() -> {
                    Spacer(modifier = Modifier.height(MediumPadding22))
                    LazyColumn {
                        items(searchState.timeZone.size) { index ->
                            val timeZone = searchState.timeZone[index]
                            SearchItem(timeZone.cityName, timeZone.time)
                        }
                    }
                }
            }
        }
    }
}
