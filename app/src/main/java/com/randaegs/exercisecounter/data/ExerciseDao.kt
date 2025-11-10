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

    @Query("Update exercises " +
            "set quantity = quantity + increment " +
            "where id in (select id from exercises order by random() limit 1)")
    fun addAmountToRandomExercise()

    @Delete
    fun delete(exercise: Exercise)
}