package com.randaegs.exercisecounter

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.randaegs.exercisecounter.ui.components.ExerciseCounterScaffold
import com.randaegs.exercisecounter.ui.theme.ExerciseCounterTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun ExerciseCounterApp() {
    ExerciseCounterTheme {
        ExerciseCounterScaffold(
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    ),
                    title = {
                        Text(
                            text = "Exercise Counter",
                            style = MaterialTheme.typography.titleLarge,
                            modifier = Modifier
                                .clickable(
                                    onClick = { openSettings()},
                                )
                        )
                    },
                    actions = {
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
                            onClick = { addExerciseCount() }
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Add,
                                contentDescription = "Add count"
                            )
                        }
                    }
                )
            },
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(6.dp)
                    .background(MaterialTheme.colorScheme.background)
                    .fillMaxSize()
            ) {
            }
        }
    }
}

fun addExerciseCount() {
}

fun openSettings(){
}