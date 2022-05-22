package org.tensorflow.lite.examples.poseestimation.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import org.tensorflow.lite.examples.poseestimation.data.Workout

@Dao
interface WorkoutDao {
    @Query("SELECT * FROM workout")
    fun getAll(): List<Workout>

    @Query("SELECT * FROM workout WHERE uid IN (:workoutUids)")
    fun getAllByIds(workoutUids: IntArray): List<Workout>

    @Insert
    fun insertAll(vararg workouts: Workout)


    @Delete
    fun delete(workout: Workout)
}