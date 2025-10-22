package com.randaegs.exercisecounter.ui.screens

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import com.randaegs.exercisecounter.ui.components.ExerciseCounterScaffold
import com.randaegs.exercisecounter.ui.components.TopBar
import com.randaegs.exercisecounter.ui.navigation.NavigationEvent

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