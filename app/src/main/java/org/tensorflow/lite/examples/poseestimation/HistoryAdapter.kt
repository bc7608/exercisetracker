package org.tensorflow.lite.examples.poseestimation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.tensorflow.lite.examples.poseestimation.data.Workout

class HistoryAdapter(private val onClick: (Workout) -> Unit) :
    ListAdapter<Workout, HistoryAdapter.HistoryViewHolder>(HistoryDiffCallback) {

    class HistoryViewHolder(itemView: View, val onClick: (Workout) -> Unit): RecyclerView.ViewHolder(itemView){
            private val typeTextView: TextView = itemView.findViewById(R.id.workout_title)
            private val countTextView: TextView = itemView.findViewById(R.id.workout_count)
            private val dateTextView: TextView = itemView.findViewById(R.id.workout_date)
            private var currentWorkout: Workout? = null

            init {
                itemView.setOnClickListener {
                    currentWorkout?.let {
                        onClick(it)
                    }
                }
            }

            fun bind(workout: Workout){
                currentWorkout = workout
                typeTextView.text = workout.type
                countTextView.text = workout.count.toString()
                dateTextView.text = workout.dateTime.toString()
            }



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.history_item, parent, false)
        return HistoryViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val workout = getItem(position)
        holder.bind(workout)
    }


}

object HistoryDiffCallback : DiffUtil.ItemCallback<Workout>() {
    override fun areItemsTheSame(oldItem: Workout, newItem: Workout): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Workout, newItem: Workout): Boolean {
        return oldItem.uid == newItem.uid
    }
}