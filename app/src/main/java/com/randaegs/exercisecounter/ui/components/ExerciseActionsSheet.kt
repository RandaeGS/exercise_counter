package com.randaegs.exercisecounter.ui.components

import android.content.Context
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.core.text.isDigitsOnly
import com.randaegs.exercisecounter.data.AppDatabase
import com.randaegs.exercisecounter.models.Exercise
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExerciseActionsSheet(
    onDismissedSheet: () -> Unit,
    exercise: Exercise
) {
    val showRemoveDialog = remember { mutableStateOf(false) }
    val showModifyDialog = remember { mutableStateOf(false) }
    ModalBottomSheet(
        onDismissRequest = onDismissedSheet,
        containerColor = MaterialTheme.colorScheme.secondary,
        contentColor = MaterialTheme.colorScheme.tertiary,
        modifier = Modifier
    ) {
        ListItem(
            headlineContent = { Text("Modify") },
            leadingContent = {
                Icon(
                    Icons.Default.Edit,
                    null
                )
            },
            colors = ListItemDefaults.colors(
                containerColor = MaterialTheme.colorScheme.secondary,
                headlineColor = MaterialTheme.colorScheme.tertiary,
                leadingIconColor = MaterialTheme.colorScheme.tertiary
            ),
            modifier = Modifier
                .combinedClickable(
                    onClick = {
                        showModifyDialog.value = true
                    }
                )
        )
        ListItem(
            headlineContent = { Text("Remove") },
            leadingContent = {
                Icon(
                    Icons.Default.Delete,
                    null
                )
            },
            colors = ListItemDefaults.colors(
                containerColor = MaterialTheme.colorScheme.secondary,
                headlineColor = MaterialTheme.colorScheme.tertiary,
                leadingIconColor = MaterialTheme.colorScheme.tertiary
            ),
            modifier = Modifier
                .combinedClickable(
                    onClick = {
                        showRemoveDialog.value = true
                    }
                )
        )
        when {
            showRemoveDialog.value -> {
                RemoveAlertDialog(
                    onConfirm = {
                        showRemoveDialog.value = false
                        onDismissedSheet()
                    },
                    onDismissRequest = {
                        showRemoveDialog.value = false
                    },
                    dialogTitle = "Remove Exercise",
                    dialogText = "This will completely remove ${exercise.name}, are you sure?",
                    exercise = exercise
                )
            }

            showModifyDialog.value -> {
                ModifyDialog(
                    onDismissRequest = {
                        showModifyDialog.value = false
                        onDismissedSheet()
                    }, exercise
                )
            }
        }
    }
}

@Composable
fun ModifyDialog(onDismissRequest: () -> Unit, exercise: Exercise) {
    Dialog(onDismissRequest = { onDismissRequest() }) {
        val context = LocalContext.current
        val scope = rememberCoroutineScope()

        val exerciseNameState = rememberTextFieldState(initialText = exercise.name)
        val exerciseIncrementState =
            rememberTextFieldState(initialText = exercise.increment.toString())
        val nameError by remember { derivedStateOf { exerciseNameState.text.trim().length < 3 } }
        val incrementError by remember {
            derivedStateOf {
                exerciseIncrementState.text
                    .isEmpty() || !exerciseIncrementState.text.isDigitsOnly()
            }
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .padding(14.dp)
            ) {
                Text("Modify Exercise", style = MaterialTheme.typography.titleLarge)
                TextField(
                    state = exerciseNameState,
                    label = { Text("Name") },
                    isError = nameError
                )
                TextField(
                    state = exerciseIncrementState,
                    label = { Text("Increment") },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number
                    ),
                    isError = incrementError
                )

                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                ) {
                    Button(
                        modifier = Modifier.padding(end = 4.dp),
                        onClick = {
                            onDismissRequest()
                        },
                    ) {
                        Text("Cancel", color = MaterialTheme.colorScheme.tertiary)
                    }
                    Button(
                        onClick = {
                            if (nameError || incrementError) return@Button
                            scope.launch {
                                val updatedExercise = exercise.copy(
                                    name = exerciseNameState.text.trim().toString(),
                                    increment = exerciseIncrementState.text.toString().toInt()
                                )
                                modifyExercise(context, updatedExercise)
                                onDismissRequest()
                            }
                        },
                    ) {
                        Text("Confirm", color = MaterialTheme.colorScheme.tertiary)
                    }
                }
            }
        }
    }
}

private suspend fun modifyExercise(context: Context, exercise: Exercise) {
    withContext(Dispatchers.IO) {
        AppDatabase.getDatabase(context)
            .exerciseDao()
            .update(exercise)
        SnackbarController.showAsync("${exercise.name} updated")
    }
}

@Composable
fun RemoveAlertDialog(
    onDismissRequest: () -> Unit,
    onConfirm: () -> Unit,
    dialogTitle: String,
    dialogText: String,
    exercise: Exercise
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    AlertDialog(
        title = {
            Text(dialogTitle, color = Color.White)
        },
        text = {
            Text(dialogText, color = Color.White)
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    scope.launch {
                        withContext(Dispatchers.IO) {
                            AppDatabase.getDatabase(context).exerciseDao().delete(exercise)
                            SnackbarController.showAsync("${exercise.name} has been removed")
                        }
                        onConfirm()
                    }
                }
            ) {
                Text("Confirm", color = Color.White)
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text("Cancel", color = Color.White)
            }
        },
        containerColor = MaterialTheme.colorScheme.primary,
    )
}