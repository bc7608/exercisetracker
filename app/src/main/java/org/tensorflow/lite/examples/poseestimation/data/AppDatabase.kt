package org.tensorflow.lite.examples.poseestimation.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import org.tensorflow.lite.examples.poseestimation.data.dao.WorkoutDao

@Database(entities = [Workout::class], version = 1)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun workoutDao(): WorkoutDao

    companion object {
        private var dbInstance : AppDatabase? = null

        fun getInstance(context: Context): AppDatabase{
            if (dbInstance == null) {
                dbInstance = Room.databaseBuilder(context, AppDatabase::class.java, "exercise_counter.db").allowMainThreadQueries().build()
            }

            return dbInstance!!
        }

        fun destroyInstance(){
            dbInstance = null
        }
    }
}