package com.example.time.presentation.components.searchscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.time.presentation.common.Dimens.MediumPadding22
import com.example.time.presentation.common.Dimens.MediumPadding16
import com.example.time.presentation.common.Dimens.SmallPadding10
import com.example.time.presentation.common.Dimens.SmallPadding12
import com.example.time.presentation.common.Dimens.SmallSize

@Composable
fun SearchItemPlaceholder() {
    Box(
        modifier = Modifier
            .height(SmallSize)
            .padding(horizontal = MediumPadding22),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = SmallPadding12, vertical = SmallPadding10),
            verticalAlignment = Alignment.CenterVertically,
        ) {

            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .wrapContentHeight(Alignment.CenterVertically)
                    .background(
                        color = Color.LightGray.copy(alpha = 0.3f),
                        shape = MaterialTheme.shapes.small
                    )
                    .height(MediumPadding16)
            )

            Spacer(modifier = Modifier.width(MediumPadding16))

            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .wrapContentHeight(Alignment.CenterVertically)
                    .background(
                        color = Color.LightGray.copy(alpha = 0.3f),
                        shape = MaterialTheme.shapes.small
                    )
                    .height(14.dp)
                    .align(Alignment.CenterVertically)
            )
        }
    }
}