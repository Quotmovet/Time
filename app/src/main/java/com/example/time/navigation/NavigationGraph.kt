package com.example.time.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.time.presentation.screens.alarmscreen.AlarmScreen
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
        composable(BottomNavItem.TimeScreen.route) { TimeScreen() }
        composable(BottomNavItem.StopwatchScreen.route) { StopwatchScreen() }
        composable(BottomNavItem.SleepScreen.route) { SleepScreen() }
    }
}