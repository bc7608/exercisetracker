package org.tensorflow.lite.examples.poseestimation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.tensorflow.lite.examples.poseestimation.data.Workout
import org.tensorflow.lite.examples.poseestimation.data.WorkoutDataSource
import org.tensorflow.lite.examples.poseestimation.data.dao.WorkoutDao
import java.util.*
import kotlin.random.Random

class WorkoutListViewModel(private val workoutDataSource: WorkoutDataSource) : ViewModel(){

    val workoutsLiveData = workoutDataSource.getWorkoutList()

    fun insertWorkout(workoutType:String?, workoutCount:Int?, workoutDate: Date){
        if (workoutType == null || workoutCount == null){
            return
        }

        val newWorkout = Workout(
            Random.nextInt(),
            workoutType,
            workoutDate,
            workoutCount
        )

        workoutDataSource.addWorkout(newWorkout)
    }

}

class WorkoutListViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WorkoutListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WorkoutListViewModel(
                workoutDataSource =  WorkoutDataSource.getDataSource(context)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

