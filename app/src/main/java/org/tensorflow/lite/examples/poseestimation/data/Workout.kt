package org.tensorflow.lite.examples.poseestimation.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Workout (
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo(name = "type") val type: String?,
    @ColumnInfo(name = "datetime") val dateTime: Date?,
    @ColumnInfo(name = "count") val count: Int?
)


