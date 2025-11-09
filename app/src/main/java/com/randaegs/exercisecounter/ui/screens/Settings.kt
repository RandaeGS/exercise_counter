package com.randaegs.exercisecounter.ui.screens

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.clearText
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.randaegs.exercisecounter.data.AppDatabase
import com.randaegs.exercisecounter.models.Exercise
import com.randaegs.exercisecounter.ui.components.ExerciseCounterScaffold
import com.randaegs.exercisecounter.ui.components.TopBar
import com.randaegs.exercisecounter.ui.navigation.NavigationEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun Settings(onNavigationEvent: (NavigationEvent) -> Unit) {
    val exerciseNameState = rememberTextFieldState()
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    ExerciseCounterScaffold(
        topBar = {
            TopBar(
                title = "Settings",
                isButtonVisible = false,
                onTitleClick = { onNavigationEvent(NavigationEvent.ToHome) })
        },
    )
    { innerPadding ->
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(innerPadding)
                .padding(5.dp)
                .fillMaxWidth()
        ) {
            OutlinedTextField(
                state = exerciseNameState,
                label = { Text("Exercise Name") },
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .weight(
                        weight = 1f
                    )
            )
            FilledIconButton(
                shape = RoundedCornerShape(size = 10.dp),
                modifier = Modifier
                    .width(60.dp),
                colors = IconButtonColors(
                    containerColor = MaterialTheme.colorScheme.tertiary,
                    contentColor = MaterialTheme.colorScheme.secondary,
                    disabledContainerColor = IconButtonDefaults.iconButtonColors().disabledContainerColor,
                    disabledContentColor = IconButtonDefaults.iconButtonColors().disabledContentColor
                ),
                onClick = {
                    val name = exerciseNameState.text.toString().trim()
                    if (name.isNotEmpty()) {
                        scope.launch {
                            addExercise(context, name)
                        }
                        exerciseNameState.clearText()
                    }
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Add count"
                )
            }
        }
    }
}

private suspend fun addExercise(context: Context, name: String) {
    val exercise = Exercise(id = 0, name = name, quantity = 0, increment = 10)
    withContext(Dispatchers.IO) {
        AppDatabase.getDatabase(context)
            .exerciseDao()
            .insert(exercise)
    }
}