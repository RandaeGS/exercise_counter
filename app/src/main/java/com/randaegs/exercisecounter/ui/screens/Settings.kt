package com.randaegs.exercisecounter.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.randaegs.exercisecounter.ui.components.ExerciseCounterScaffold
import com.randaegs.exercisecounter.ui.components.TopBar
import com.randaegs.exercisecounter.ui.navigation.NavigationEvent

@Composable
fun Settings(onNavigationEvent: (NavigationEvent) -> Unit) {
    ExerciseCounterScaffold(
        topBar = {
            TopBar(
                title = "Settings",
                isButtonVisible = false,
                onTitleClick = { onNavigationEvent(NavigationEvent.ToHome) })
        },
    )
    { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Text("Hola")
            OutlinedTextField(
                state = rememberTextFieldState(),
                label = { Text("Name") },
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}