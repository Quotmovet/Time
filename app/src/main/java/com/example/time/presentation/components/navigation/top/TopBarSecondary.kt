package com.example.time.presentation.components.navigation.top

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.time.app.navigation.Screens
import com.example.time.presentation.components.navigation.top.elements.CustomTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarSecondary(navController: NavController) {
    val title = Screens.SettingsScreen.title
    val painterIcon = painterResource(id = Screens.BackScreen.iconResId)

    CustomTopAppBar(
        title = stringResource(id = title),
        icon = painterIcon,
        onIconClick = { navController.popBackStack() },
    )
}
