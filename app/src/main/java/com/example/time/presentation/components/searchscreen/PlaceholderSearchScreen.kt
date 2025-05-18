package com.example.time.presentation.components.searchscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.time.R
import com.example.time.presentation.common.Dimens.LargeIconsSize118
import com.example.time.presentation.common.theme.Theme

@Composable
fun PlaceholderSearchScreen() {
    Box(
        //contentAlignment =
    ) {
        Column {
            Icon(
                painter = painterResource(R.drawable.ic_search),
                contentDescription = null,
                modifier = Modifier
                    .size(LargeIconsSize118),
                tint = MaterialTheme.colorScheme.secondary
            )
            Text(
                text = stringResource(R.string.searchCities),
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}

@Preview
@Composable
fun PreviewPlaceholderSearchScreen(){
    Theme {
        PlaceholderSearchScreen()
    }
}