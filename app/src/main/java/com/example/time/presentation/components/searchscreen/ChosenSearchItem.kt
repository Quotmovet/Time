package com.example.time.presentation.components.searchscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.time.R
import com.example.time.presentation.common.Dimens.MediumPadding22
import com.example.time.presentation.common.Dimens.SmallPadding10
import com.example.time.presentation.common.Dimens.SmallPadding4
import com.example.time.presentation.common.Dimens.SmallSize
import com.example.time.presentation.common.theme.Theme

@Composable
fun ChosenSearchItem(
    // citiesAndTime
){
    Box(
        modifier = Modifier
            .height(SmallSize)
            .background(color = MaterialTheme.colorScheme.onSecondary),
        contentAlignment = Alignment.Center
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MediumPadding22, vertical = SmallPadding10),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                modifier = Modifier.padding(end = SmallPadding4),
                painter = painterResource(R.drawable.ic_checkmark),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.secondary
            )
            Text(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .wrapContentHeight(Alignment.CenterVertically),
                text = "Moscow",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .wrapContentHeight(Alignment.CenterVertically),
                text = "19:00",
                textAlign = TextAlign.End,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Preview
@Composable
fun PreviewChosenSearchItem(){
    Theme {
        ChosenSearchItem()
    }
}