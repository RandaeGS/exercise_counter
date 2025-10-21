package com.example.exercisecounter.ui.screens

import androidx.compose.runtime.Composable
import com.example.exercisecounter.ui.components.ExerciseCounterScaffold
import com.example.exercisecounter.ui.components.TopBar
import com.example.exercisecounter.ui.navigation.NavigationEvent

@Composable
fun Settings(onNavigationEvent: (NavigationEvent) -> Unit) {
    ExerciseCounterScaffold(
        topBar = {
            TopBar(
                title = "Settings",
                isButtonVisible = false,
                onTitleClick = { onNavigationEvent(NavigationEvent.ToHome) })
        }
    ) {
    }
}