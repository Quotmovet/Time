package com.example.time.presentation.screens.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.time.navigation.BottomNavItem
import com.example.time.navigation.NavGraph
import androidx.compose.runtime.getValue
import com.example.time.presentation.components.navigation.bottom.BottomNavigationBar
import com.example.time.presentation.components.navigation.top.TopBarMain
import com.example.time.presentation.components.navigation.top.TopBarSecondary

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val currentScreen = BottomNavItem.items.find { it.route == currentRoute }
        ?: BottomNavItem.TimeScreen

    val isMainScreen = currentRoute in listOf("alarm", "timer", "time", "stopwatch", "sleep")

    val hideBottomNavRoutes = listOf("settings", "search")
    val hideTopNavRoutes = listOf("search")

    val showBottomNav = !hideBottomNavRoutes.contains(currentRoute)
    val showTopNav = !hideTopNavRoutes.contains(currentRoute)

    Scaffold(
        topBar = {
            if(showTopNav) {
                if (isMainScreen) {
                    TopBarMain(
                        currentScreen = currentScreen,
                        navController = navController
                    )
                } else {
                    TopBarSecondary( navController = navController )
                }
            }
        },

        bottomBar = {
            if(showBottomNav) {
                BottomNavigationBar(
                    currentScreen = currentScreen,
                    onItemClick = { item ->
                        if (currentScreen.route != item.route) {
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    }
                )
            }
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            NavGraph(navController = navController)
        }
    }
}