package com.example.time.presentation.components.searchscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.time.R
import com.example.time.presentation.common.Dimens.LargeIconsSize118
import com.example.time.presentation.common.Dimens.MediumPadding22

@Composable
fun PlaceholderSearchScreen() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = MediumPadding22),
        contentAlignment = Alignment.Center
    ) {
        Column {
            Icon(
                painter = painterResource(R.drawable.ic_search),
                contentDescription = null,
                modifier = Modifier
                    .size(LargeIconsSize118),
                tint = MaterialTheme.colorScheme.tertiary
            )
            Text(
                modifier = Modifier
                    .align(alignment = Alignment.CenterHorizontally),
                text = stringResource(R.string.searchCities),
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.tertiary
            )
        }
    }
}
