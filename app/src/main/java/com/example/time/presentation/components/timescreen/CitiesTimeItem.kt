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
import com.example.time.presentation.common.Dimens.MainSize
import com.example.time.presentation.common.Dimens.MediumPadding16
import com.example.time.presentation.common.Dimens.PrimaryCorner
import com.example.time.presentation.common.Dimens.SmallPadding12

@Composable
fun CitiesTimeItem(
    cityName: String,
    time: String,
    offset: String
){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(MainSize)
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
                    text = cityName,
                    maxLines = 1,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = offset,
                    maxLines = 1,
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }

            Text(
                modifier = Modifier
                    .weight(0.3f),
                text = time,
                textAlign = TextAlign.End,
                maxLines = 1,
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}
