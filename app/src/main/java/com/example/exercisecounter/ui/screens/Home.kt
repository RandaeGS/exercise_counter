package com.example.exercisecounter.ui.screens

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.exercisecounter.ui.components.ExerciseCounterScaffold
import com.example.exercisecounter.ui.components.TopBar
import com.example.exercisecounter.ui.navigation.NavigationEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(onNavigationEvent: (NavigationEvent) -> Unit) {
    ExerciseCounterScaffold(
        topBar = {
            TopBar(
                onTitleClick = {
                    onNavigationEvent(NavigationEvent.ToSettings)
                }
            )
        }
    ) {

    }
}