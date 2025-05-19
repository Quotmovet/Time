package com.example.time.presentation.components.navigation.top

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
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.time.navigation.BottomNavItem
import com.example.time.navigation.Screens
import com.example.time.presentation.common.Dimens.SmallPadding4
import com.example.time.presentation.common.util.sizes.responsiveIconSize

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
                text = stringResource(id = currentScreen.title),
                style = MaterialTheme.typography.titleLarge
            )
        },
        actions = {
            IconButton( onClick = { navController.navigate(Screens.SettingsScreen.route) }
            ) {
                Icon(
                    painter = painterResource(id = Screens.SettingsScreen.iconResId),
                    modifier = Modifier
                        .size(iconSize)
                        .padding(SmallPadding4),
                    contentDescription = stringResource(id = Screens.SettingsScreen.title)
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
            titleContentColor = MaterialTheme.colorScheme.onBackground,
            actionIconContentColor = MaterialTheme.colorScheme.onBackground
        )
    )
}
