package com.randaegs.exercisecounter.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.randaegs.exercisecounter.data.AppDatabase
import com.randaegs.exercisecounter.models.Exercise
import com.randaegs.exercisecounter.ui.components.ExerciseCounterScaffold
import com.randaegs.exercisecounter.ui.components.ExerciseItem
import com.randaegs.exercisecounter.ui.components.TopBar
import com.randaegs.exercisecounter.ui.navigation.NavigationEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(onNavigationEvent: (NavigationEvent) -> Unit) {
    val context = LocalContext.current
    val db = AppDatabase.getDatabase(context)
    val exercisesFlow: Flow<List<Exercise>> = db.exerciseDao().getAll()
    val exercises by exercisesFlow.collectAsState(initial = emptyList())
    ExerciseCounterScaffold(
        topBar = {
            TopBar(
                onTitleClick = {
                    onNavigationEvent(NavigationEvent.ToSettings)
                }
            )
        },
        modifier = Modifier
            .background(MaterialTheme.colorScheme.secondary)
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            exercises.forEach { exercise ->
                ExerciseItem(exercise)
            }
        }
    }
}