package org.tensorflow.lite.examples.poseestimation.data.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import org.tensorflow.lite.examples.poseestimation.data.Workout

@Dao
interface WorkoutDao {
    @Query("SELECT * FROM workout ORDER BY datetime")
    fun getAll(): Flow<MutableList<Workout>>

    @Query("SELECT * FROM workout WHERE uid IN (:workoutUids)")
    suspend fun getAllByIds(workoutUids: IntArray): List<Workout>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg workouts: Workout)

    @Query("DELETE FROM workout")
    suspend fun deleteAll()

    @Delete
    suspend fun delete(workout: Workout)
}