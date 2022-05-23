package org.tensorflow.lite.examples.poseestimation

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.tensorflow.lite.examples.poseestimation.data.AppDatabase
import org.tensorflow.lite.examples.poseestimation.data.AppDatabaseRepository

class ExerciseCounter : Application() {
    private val applicationScope = CoroutineScope(SupervisorJob())

    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    private val database by lazy { AppDatabase.getDatabase(this,applicationScope) }
    val repository by lazy { AppDatabaseRepository(database.workoutDao()) }
}