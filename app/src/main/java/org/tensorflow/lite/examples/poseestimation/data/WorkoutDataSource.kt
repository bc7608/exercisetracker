package org.tensorflow.lite.examples.poseestimation.data

import android.content.Context
import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import org.tensorflow.lite.examples.poseestimation.data.dao.WorkoutDao

class WorkoutDataSource(context: Context) {
    private val initialWorkoutList = AppDatabase.getInstance(context).workoutDao().getAll()
    private val workoutsLiveData = MutableLiveData(initialWorkoutList)

    /* Adds Workout to liveData and posts value. */
    fun addWorkout(Workout: Workout) {
        val currentList = workoutsLiveData.value
        if (currentList == null) {
            workoutsLiveData.postValue(listOf(Workout))
        } else {
            val updatedList = currentList.toMutableList()
            updatedList.add(0, Workout)
            workoutsLiveData.postValue(updatedList)
        }
    }

    fun getWorkoutList(): LiveData<List<Workout>> {
        return workoutsLiveData
    }

    companion object {
        private var INSTANCE: WorkoutDataSource? = null

        fun getDataSource(context: Context): WorkoutDataSource {
            return synchronized(WorkoutDataSource::class) {
                val newInstance = INSTANCE ?: WorkoutDataSource(context)
                INSTANCE = newInstance
                newInstance
            }
        }
    }

}