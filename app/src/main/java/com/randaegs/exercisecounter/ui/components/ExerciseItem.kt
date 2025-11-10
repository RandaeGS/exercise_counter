package com.randaegs.exercisecounter.ui.components

import android.content.Context
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.randaegs.exercisecounter.data.AppDatabase
import com.randaegs.exercisecounter.data.ExerciseDao
import com.randaegs.exercisecounter.models.Exercise
import com.randaegs.exercisecounter.ui.theme.Typography
import com.randaegs.exercisecounter.ui.theme.danger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun ExerciseItem(exercise: Exercise) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .border(width = 0.6.dp, color = Color.White, shape = RoundedCornerShape(size = 10.dp))
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .height(50.dp)
                .padding(start = 8.dp)
        ) {
            Text(
                exercise.name.replaceFirstChar { it.uppercase() },
                style = Typography.bodyLarge,
                modifier = Modifier.weight(1f)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = exercise.quantity.toString(),
                    modifier = Modifier
                        .padding(end = 10.dp)
                )
                FilledIconButton(
                    shape = RoundedCornerShape(topEnd = 10.dp, bottomEnd = 10.dp),
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(70.dp),
                    colors = IconButtonColors(
                        containerColor = danger,
                        contentColor = MaterialTheme.colorScheme.tertiary,
                        disabledContainerColor = IconButtonDefaults.iconButtonColors().disabledContainerColor,
                        disabledContentColor = IconButtonDefaults.iconButtonColors().disabledContentColor
                    ),
                    onClick = {
                        scope.launch {
                            subtractExerciseQuantity(exercise, context)
                        }
                    }
                ) {
                    Row {
                        Icon(
                            imageVector = Minus,
                            contentDescription = "Subtract ${exercise.increment}"
                        )
                        Text(exercise.increment.toString())
                    }
                }
            }
        }

    }
}

private suspend fun subtractExerciseQuantity(exercise: Exercise, context: Context) {
    withContext(Dispatchers.IO) {
        val dao = AppDatabase.getDatabase(context)
            .exerciseDao()
        val updated = exercise.copy(
            quantity = exercise.quantity - exercise.increment
        )
        dao.update(updated)
        SnackbarController.showAsync(
            """
        ${exercise.name.replaceFirstChar { it.uppercase() }}: ${exercise.increment} DONE
    """.trimIndent()
        )
    }
}