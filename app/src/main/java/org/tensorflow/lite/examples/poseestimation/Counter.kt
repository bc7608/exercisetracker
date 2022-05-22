package org.tensorflow.lite.examples.poseestimation

import android.content.Context
import android.speech.tts.TextToSpeech
import android.speech.tts.TextToSpeech.QUEUE_ADD
import android.util.Log
import android.widget.Toast
import org.tensorflow.lite.examples.poseestimation.camera.CameraSource
import org.tensorflow.lite.examples.poseestimation.data.BodyPart
import org.tensorflow.lite.examples.poseestimation.data.Person
import java.util.*

class Counter(
    context: Context,
    private val listener: CounterListener? = null
) {

    val tts: TextToSpeech




    init {
        tts = TextToSpeech(context
        ) { status ->
            if (status == TextToSpeech.SUCCESS) {
            }
        }
    }

    val MIN_AMPLITUDE = 40
    val REP_THRESHOLD = 0.8
    val MIN_CONFIDENCE = 0.5
    val EXERCISE_CHANGE_TRESHOLD = 450.0f

    var count = 0

    var first = true
    var goal = 1
    var prev_y = 0.0f
    var prev_dy = 0.0f
    var top = 0
    var bottom = 0



    var leftWristY = 0.0f
    var rightWristY = 0.0f

    //push ups or squats
    var workoutType = "UNKNOWN"


    fun OnFrame(person: Person, context: Context) : Int {


        if (person.keyPoints[BodyPart.NOSE.ordinal].score >= MIN_CONFIDENCE) {
            val y = 1000 - person.keyPoints[BodyPart.NOSE.ordinal].coordinate.y

            val leftWristY = 1000 - person.keyPoints[BodyPart.LEFT_WRIST.ordinal].coordinate.y
            val rightWristY = 1000 - person.keyPoints[BodyPart.RIGHT_WRIST.ordinal].coordinate.y



            if (leftWristY > EXERCISE_CHANGE_TRESHOLD && rightWristY > EXERCISE_CHANGE_TRESHOLD){
                if (workoutType == "PUSH_UPS"){
                    count = 0

                }
                if (first || workoutType == "PUSH_UPS"){
                    Toast.makeText(context, "Now doing Squats", Toast.LENGTH_LONG).show()
                }
                workoutType = "SQUATS"
            }
            else if (leftWristY < EXERCISE_CHANGE_TRESHOLD && rightWristY < EXERCISE_CHANGE_TRESHOLD){
                if (workoutType == "SQUATS"){
                    count = 0

                }
                if (first || workoutType == "SQUATS"){
                    Toast.makeText(context, "Now doing Pushups", Toast.LENGTH_LONG).show()
                }

                workoutType = "PUSH_UPS"
            }


            //Log.i("HANDS", "$leftWristY: LEFT WRIST")
            //Log.i("HANDS", "$rightWristY: RIGHT WRIST")

            val dy = y - prev_y
            if (!first) {
                if (bottom != 0 && top != 0) {
                    if (goal == 1 && dy > 0 && (y - bottom) > (top - bottom) * REP_THRESHOLD) {
                        if (top - bottom > MIN_AMPLITUDE) {
                            count++
                            goal = -1
                            OnRep()
                        }
                    }
                    else if (goal == -1 && dy < 0 && (top - y) > (top - bottom) * REP_THRESHOLD) {
                        goal = 1
                    }
                }

                if (dy < 0 && prev_dy >= 0 && prev_y - bottom > MIN_AMPLITUDE) {
                    top = prev_y.toInt()
                }
                else if (dy > 0 && prev_dy <= 0 && top - prev_y > MIN_AMPLITUDE) {
                    bottom = prev_y.toInt()
                }

            }

            first = false
            prev_y = y
            prev_dy = dy
            listener?.onExcerciseChangedListener(workoutType)
        }

        return count
    }




    fun OnRep() {
        tts.speak(count.toString(), QUEUE_ADD, null)
    }

    interface CounterListener {
        fun onExcerciseChangedListener(workoutType: String)
    }


}