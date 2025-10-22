package com.randaegs.exercisecounter.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exercises")
data class Exercise (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val increment: Int,
    val quantity: Int
)