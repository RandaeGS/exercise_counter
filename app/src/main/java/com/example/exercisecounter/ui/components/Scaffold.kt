package com.example.exercisecounter.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ExerciseCounterScaffold(
    modifier: Modifier = Modifier,
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit
){
    Scaffold (
        modifier = modifier,
        topBar = topBar,
        content = content,
        bottomBar = bottomBar
    )
}