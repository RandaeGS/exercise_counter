package com.randaegs.exercisecounter.ui.components

import androidx.compose.foundation.clickable
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
import androidx.compose.ui.unit.dp
import com.randaegs.exercisecounter.addExerciseCount

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title: String = "Exercise Counter",
    onTitleClick: (() -> Unit)? = null,
    isButtonVisible: Boolean = true,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .clickable(
                        onClick = {
                            onTitleClick?.invoke()
                        },
                    )
            )
        },
        actions = {
            if (isButtonVisible) {
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
        }
    )
}
