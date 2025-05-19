package com.example.time.presentation.components.searchscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.time.presentation.common.Dimens.MediumPadding22
import com.example.time.presentation.common.Dimens.SmallPadding10
import com.example.time.presentation.common.Dimens.SmallPadding12
import com.example.time.presentation.common.Dimens.SmallSize
import com.example.time.presentation.common.theme.Theme

@Composable
fun SearchItem(
    // citiesAndTime
){

    Box(
        modifier = Modifier
            .height(SmallSize)
            .padding(horizontal = MediumPadding22),
        contentAlignment = Alignment.Center
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = SmallPadding12, vertical = SmallPadding10),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .wrapContentHeight(Alignment.CenterVertically),
                text = "Moscow",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .wrapContentHeight(Alignment.CenterVertically),
                text = "19:00",
                textAlign = TextAlign.End,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}