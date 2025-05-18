package com.example.time.presentation.components.timescreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.time.presentation.common.Dimens.MainSize
import com.example.time.presentation.common.Dimens.MediumPadding16
import com.example.time.presentation.common.Dimens.MediumPadding22
import com.example.time.presentation.common.Dimens.PrimaryCorner
import com.example.time.presentation.common.Dimens.SmallPadding12
import com.example.time.presentation.common.theme.Theme

@Composable
fun CitiesTimeItem(
    // citiesTimeItemElements: CitiesTimeItemElements
){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(MainSize)
            .padding(horizontal = MediumPadding22)
            .background(
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = RoundedCornerShape(PrimaryCorner)
            ),
    ){
        Row(
            modifier = Modifier.padding(horizontal = MediumPadding16, vertical = SmallPadding12),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .weight(0.7f)
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Novosibirsk",
                    maxLines = 1,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "+4 h.",
                    maxLines = 1,
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }

            Text(
                modifier = Modifier
                    .weight(0.3f),
                text = "15:39",
                textAlign = TextAlign.End,
                maxLines = 1,
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}

@Preview
@Composable
fun PreviewCitiesTimeItem(){
    Theme{
        CitiesTimeItem()
    }
}