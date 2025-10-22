package com.randaegs.exercisecounter.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.randaegs.exercisecounter.models.Exercise

@Dao
interface ExerciseDao {
    @Query("SELECT * FROM exercises")
    fun getAll(): List<Exercise>

    @Insert
    fun insert(vararg exercise: Exercise)

    @Delete
    fun delete(exercise: Exercise)
}