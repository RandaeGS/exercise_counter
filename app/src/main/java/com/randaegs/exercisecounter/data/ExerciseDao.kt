package com.randaegs.exercisecounter.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.randaegs.exercisecounter.models.Exercise
import kotlinx.coroutines.flow.Flow

@Dao
interface ExerciseDao {
    @Query("SELECT * FROM exercises")
    fun getAll(): Flow<List<Exercise>>

    @Insert
    fun insert(vararg exercise: Exercise)

    @Query("""
        SELECT * FROM exercises 
        ORDER BY random() 
        LIMIT 1
    """)
    fun searchRandomExercise() : Exercise?

    @Update
    fun update(vararg exercise: Exercise)

    @Delete
    fun delete(exercise: Exercise)
}