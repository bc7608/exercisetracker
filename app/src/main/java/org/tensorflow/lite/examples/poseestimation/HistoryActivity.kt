package org.tensorflow.lite.examples.poseestimation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.tensorflow.lite.examples.poseestimation.data.AppDatabaseViewModel
import org.tensorflow.lite.examples.poseestimation.data.Workout

class HistoryActivity : AppCompatActivity() {


    private var historyAdapter : HistoryAdapter? = null


    private val appDatabaseViewModel: AppDatabaseViewModel by viewModels {
        AppDatabaseViewModel.AppDatabaseViewModelFactory((application as ExerciseCounter).repository)
    }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        historyAdapter = HistoryAdapter {workout -> adapterOnClick(workout) }
        val recyclerView: RecyclerView = findViewById(R.id.recycler_view_history)
        recyclerView.adapter = historyAdapter

        //appDatabaseViewModel.deleteAll()

        appDatabaseViewModel.allWorkoutItems.observe(this) {
            it?.let {
                historyAdapter!!.submitList(it as MutableList<Workout>)

            }
        }

        val fab: View = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            appDatabaseViewModel.deleteAll()
        }
    }


    private fun adapterOnClick(workout: Workout) {
        //historyAdapter?.notifyDataSetChanged()
        appDatabaseViewModel.delete(workout)
    }
}