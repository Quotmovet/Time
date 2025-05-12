package com.example.time.presentation.components.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.time.navigation.TopNavItem
import com.example.time.presentation.common.navigation.CustomTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarSecondary(
    navController: NavController
) {

    val title = TopNavItem.SettingsScreen.title
    val painterIcon = painterResource(id = TopNavItem.BackScreen.iconResId)

    CustomTopAppBar(
        title = title,
        icon = painterIcon,
        onIconClick = { navController.popBackStack() }
    )

}
