package com.example.exercisecounter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.exercisecounter.ui.navigation.NavigationEvent
import com.example.exercisecounter.ui.navigation.Routes
import com.example.exercisecounter.ui.screens.Home
import com.example.exercisecounter.ui.screens.Settings
import com.example.exercisecounter.ui.theme.ExerciseCounterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ExerciseCounterTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.Home.route) {
        composable(Routes.Home.route) {
            Home(
                onNavigationEvent = { event ->
                    when (event) {
                        NavigationEvent.GoBack -> navController.popBackStack()
                        NavigationEvent.ToSettings -> navController.navigate(Routes.Settings.route)
                        else -> null
                    }
                }
            )
        }

        composable(Routes.Settings.route) {
            Settings(
                onNavigationEvent = { event ->
                    when(event) {
                        NavigationEvent.GoBack -> navController.popBackStack()
                        NavigationEvent.ToHome -> navController.navigate(Routes.Home.route)
                        else -> null
                    }
                }
            )
        }
    }
}