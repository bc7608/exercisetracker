package org.tensorflow.lite.examples.poseestimation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.RecyclerView
import org.tensorflow.lite.examples.poseestimation.data.Workout

class HistoryActivity : AppCompatActivity() {

    private val workoutListViewModel by viewModels<WorkoutListViewModel> {WorkoutListViewModelFactory(this)  }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        val historyAdapter = HistoryAdapter {workout -> adapterOnClick(workout) }

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view_history)
        recyclerView.adapter = historyAdapter

        workoutListViewModel.workoutsLiveData.observe(this) {
            it?.let {
                historyAdapter.submitList(it as MutableList<Workout>)
            }
        }
    }


    private fun adapterOnClick(workout: Workout) {
        TODO("TODO")
    }
}