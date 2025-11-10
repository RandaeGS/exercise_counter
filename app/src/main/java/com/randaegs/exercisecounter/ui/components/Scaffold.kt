package com.randaegs.exercisecounter.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.randaegs.exercisecounter.ui.theme.danger

@Composable
fun ExerciseCounterScaffold(
    modifier: Modifier = Modifier,
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit
){
    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        SnackbarController.current = snackBarHostState
    }

    Scaffold (
        modifier = modifier,
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState) { data ->
                Snackbar(
                    snackbarData = data,
                    containerColor = MaterialTheme.colorScheme.tertiary,
                    actionColor = danger
                )
            }
        },
        topBar = topBar,
        content = content,
        bottomBar = bottomBar
    )
}