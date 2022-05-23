package org.tensorflow.lite.examples.poseestimation.data

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow
import org.tensorflow.lite.examples.poseestimation.data.dao.WorkoutDao

class AppDatabaseRepository(private val workoutDao: WorkoutDao) {

    val allWorkoutItems:Flow<MutableList<Workout>> = workoutDao.getAll()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(workout: Workout){
        workoutDao.insertAll(workout)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun delete(workout: Workout){
        workoutDao.delete(workout)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deleteAll(){
        workoutDao.deleteAll()
    }


}