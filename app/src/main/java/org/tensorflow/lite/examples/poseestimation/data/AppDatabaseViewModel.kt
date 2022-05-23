package org.tensorflow.lite.examples.poseestimation.data

import androidx.lifecycle.*
import kotlinx.coroutines.launch

class AppDatabaseViewModel(private val appDatabaseRepository: AppDatabaseRepository) : ViewModel() {

    val allWorkoutItems : LiveData<MutableList<Workout>> = appDatabaseRepository.allWorkoutItems.asLiveData()

    fun insert(workout: Workout) = viewModelScope.launch {
        appDatabaseRepository.insert(workout)
    }

    fun delete(workout: Workout) = viewModelScope.launch {
        appDatabaseRepository.delete(workout)
    }

    fun deleteAll() = viewModelScope.launch {
        appDatabaseRepository.deleteAll()
    }

    class AppDatabaseViewModelFactory(private val appDatabaseRepository: AppDatabaseRepository) : ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(AppDatabaseViewModel::class.java)){
                @Suppress("UNCHECKED_CAST")
                return AppDatabaseViewModel(appDatabaseRepository) as T
            }
            throw IllegalArgumentException("Unknown VieModel Class")
        }
    }
}