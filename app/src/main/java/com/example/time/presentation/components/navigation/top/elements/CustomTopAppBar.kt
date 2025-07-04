package com.example.time.presentation.components.navigation.top.elements

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import com.example.time.presentation.common.Dimens.LargePadding64
import com.example.time.presentation.common.Dimens.SmallPadding4
import com.example.time.presentation.common.Dimens.SmallPadding8
import com.example.time.presentation.common.util.sizes.main.rememberResponsiveSizes

@Composable
fun CustomTopAppBar(
    title: String,
    icon: Painter,
    onIconClick: () -> Unit,
) {
    val iconSize = rememberResponsiveSizes()

    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .height(LargePadding64)
                .padding(horizontal = SmallPadding4),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(onClick = onIconClick) {
            Icon(
                painter = icon,
                modifier =
                    Modifier
                        .size(iconSize.iconSize)
                        .padding(SmallPadding4),
                contentDescription = null,
            )
        }

        Spacer(modifier = Modifier.width(SmallPadding8))

        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground,
        )
    }
}
