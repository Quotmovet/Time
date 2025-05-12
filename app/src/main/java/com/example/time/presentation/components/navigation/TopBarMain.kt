package com.example.time.presentation.components.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.time.navigation.BottomNavItem
import com.example.time.navigation.TopNavItem
import com.example.time.presentation.theme.Dimens.SmallPadding4
import com.example.time.util.sizes.responsiveIconSize

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarMain(
    currentScreen: BottomNavItem,
    navController: NavController
) {

    val iconSize = responsiveIconSize()

    TopAppBar(
        title = {
            Text(
                text = currentScreen.title,
                style = MaterialTheme.typography.titleLarge
            )
        },
        actions = {
            IconButton( onClick = {
                navController.navigate(TopNavItem.SettingsScreen.route)
            } ) {
                Icon(
                    painter = painterResource(id = TopNavItem.SettingsScreen.iconResId),
                    modifier = Modifier
                        .size(iconSize)
                        .padding(SmallPadding4),
                    contentDescription = TopNavItem.SettingsScreen.title
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surface,
            titleContentColor = MaterialTheme.colorScheme.onSurface,
            actionIconContentColor = MaterialTheme.colorScheme.onSurface
        )
    )
}
