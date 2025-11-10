package com.randaegs.exercisecounter.ui.components

import androidx.compose.material3.SnackbarDefaults
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.time.Duration

object SnackbarController {
    private var _hostState: SnackbarHostState? = null

    var current: SnackbarHostState
        get() = _hostState ?: throw IllegalStateException("SnackbarHostState not initialized")
        set(value) {_hostState = value}

    suspend fun show(
        message: String,
    ): SnackbarResult {
        return current.showSnackbar(
            message,
            actionLabel = "Dismiss",
            duration = SnackbarDuration.Short)
    }

    fun showAsync(
        message: String
    ) {
        CoroutineScope(Dispatchers.Main).launch {
            val result = show(message)
        }
    }
}