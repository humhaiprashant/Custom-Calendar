package com.example.customcalendar.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.customcalendar.constants.Constants
import com.example.customcalendar.presentation.calender.CalendarScreen
import com.example.customcalendar.presentation.tasklist.TaskListScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Constants.NAV_DEST_CALENDAR) {
        composable(Constants.NAV_DEST_CALENDAR) {
            CalendarScreen(navController)
        }
        composable(Constants.NAV_DEST_TASKS) {
            TaskListScreen(navController)
        }
    }
}
