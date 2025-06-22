package com.example.time.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.time.presentation.screens.alarmscreen.AlarmScreen
import com.example.time.presentation.screens.alarmscreen.AlarmScreenDuringAlarm
import com.example.time.presentation.screens.searchscreen.SearchScreen
import com.example.time.presentation.screens.settingsscreen.SettingsScreen
import com.example.time.presentation.screens.sleepscreen.SleepScreen
import com.example.time.presentation.screens.stopwatchscreen.StopwatchScreen
import com.example.time.presentation.screens.timerscreen.TimerScreen
import com.example.time.presentation.screens.timescreen.TimeScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    startDestination: String = BottomNavItem.TimeScreen.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(BottomNavItem.AlarmScreen.route) { AlarmScreen() }
        composable(BottomNavItem.TimerScreen.route) { TimerScreen() }
        composable(BottomNavItem.TimeScreen.route) { TimeScreen(navController) }
        composable(BottomNavItem.StopwatchScreen.route) { StopwatchScreen() }
        composable(BottomNavItem.SleepScreen.route) { SleepScreen() }

        composable(Screens.SettingsScreen.route) { SettingsScreen() }
        composable(Screens.SearchScreen.route) { SearchScreen(navController) }

        composable(
            route = "alarm_screen_during_alarm/{alarmId}",
            arguments = listOf(navArgument("alarmId") { type = NavType.IntType })
        ) { backStackEntry ->
            val alarmId = backStackEntry.arguments?.getInt("alarmId") ?: return@composable
            AlarmScreenDuringAlarm(alarmId = alarmId)
        }
    }
}