package com.randaegs.exercisecounter.ui.components

import androidx.compose.foundation.combinedClickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
                        showModifyDialog.value = true
                    }
                )
        )
        when {
            showModifyDialog.value -> {
                RemoveAlertDialog(
                    onConfirm = {
                        showModifyDialog.value = false
                        onDismissedSheet()
                    },
                    onDismissRequest = {
                        showModifyDialog.value = false
                    },
                    dialogTitle = "Remove Exercise",
                    dialogText = "This will completely remove ${exercise.name}, are you sure?",
                    exercise = exercise
                )
            }
        }
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