package com.example.time.presentation.components.navigation.bottom

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.time.navigation.BottomNavItem
import com.example.time.presentation.common.Dimens.SmallPadding4
import com.example.time.presentation.common.util.effects.noRippleInteractionSource
import com.example.time.presentation.common.util.sizes.responsiveIconSize
import com.example.time.presentation.common.util.sizes.responsiveTextSize

@Composable
fun BottomNavigationBar(
    currentScreen: BottomNavItem,
    onItemClick: (BottomNavItem) -> Unit,
) {
    val iconSize = responsiveIconSize()
    val textSize = responsiveTextSize()

    NavigationBar {
        BottomNavItem.items.forEach { item ->
            val interactionSource = remember { noRippleInteractionSource() }
            val isSelected = currentScreen.route == item.route

            NavigationBarItem(
                selected = isSelected,
                onClick = { onItemClick(item) },
                icon = {
                    Icon(
                        painter = painterResource(id = item.iconResId),
                        contentDescription = stringResource(id = item.title),
                        modifier = Modifier
                            .size(if (isSelected) iconSize * 1.1f else iconSize)
                            .animateContentSize(
                                animationSpec = tween(
                                    durationMillis = 400,
                                    easing = FastOutSlowInEasing
                                )
                            )
                            .padding(SmallPadding4)
                    )
                },
                label = {
                    Text(
                        text = stringResource(id = item.title),
                        fontSize = textSize
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.onSecondary,
                    selectedTextColor = MaterialTheme.colorScheme.onSecondary,
                    unselectedIconColor = MaterialTheme.colorScheme.onPrimary,
                    unselectedTextColor = MaterialTheme.colorScheme.onPrimary,
                    indicatorColor = Color.Transparent
                ),
                interactionSource = interactionSource
            )
        }
    }
}