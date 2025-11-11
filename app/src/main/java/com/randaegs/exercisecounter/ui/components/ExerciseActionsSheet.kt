package com.randaegs.exercisecounter.ui.components

import androidx.compose.foundation.background
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemColors
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.randaegs.exercisecounter.ui.theme.danger

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExerciseActionsSheet(
    onDismissedSheet: () -> Unit
) {
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
        )
    }
}