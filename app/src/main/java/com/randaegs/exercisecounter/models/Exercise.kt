package com.randaegs.exercisecounter.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exercises")
data class Exercise (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var name: String,
    var increment: Int,
    var quantity: Int
)