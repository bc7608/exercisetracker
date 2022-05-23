package org.tensorflow.lite.examples.poseestimation.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.tensorflow.lite.examples.poseestimation.data.dao.WorkoutDao
import java.util.*
import kotlin.random.Random

@Database(entities = [Workout::class], version = 1)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun workoutDao(): WorkoutDao

    companion object {
        @Volatile
        private var INSTANCE : AppDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope):AppDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "food_item_database"
                ).addCallback(AppDatabaseCallback(scope))
                    .build()

                INSTANCE = instance

                // return instance
                instance
            }
        }

        private class AppDatabaseCallback(val scope: CoroutineScope):RoomDatabase.Callback(){
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)

                INSTANCE?.let { workoutRoomDB ->
                    scope.launch {
                        // if you want to populate database
                        // when RoomDatabase is created
                        // populate here
                        //workoutRoomDB.workoutDao().deleteAll()
                        var workout = Workout(
                            type = "SQUATS",
                            dateTime = Date(),
                            count = 15,
                            uid = Random.nextInt()
                        )
                        workoutRoomDB.workoutDao().insertAll(workout)
                        workout = Workout(
                            type = "PUSH UPS",
                            dateTime = Date(),
                            count = 35,
                            uid = Random.nextInt()
                        )
                        workoutRoomDB.workoutDao().insertAll(workout)
                        workout = Workout(
                            type = "PUSH UPS",
                            dateTime = Date(),
                            count = 15,
                            uid = Random.nextInt()
                        )
                        workoutRoomDB.workoutDao().insertAll(workout)
                        workout = Workout(
                            type = "SQUATS",
                            dateTime = Date(),
                            count = 5,
                            uid = Random.nextInt()
                        )
                        workoutRoomDB.workoutDao().insertAll(workout)

                    }
                }
            }
        }
    }
}