package com.fitnessapp.ui.tracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fitnessapp.R
import com.fitnessapp.model.WorkoutSession

class WorkoutSessionAdapter(private var sessions: List<WorkoutSession>) :
    RecyclerView.Adapter<WorkoutSessionAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvDate: TextView = view.findViewById(R.id.tvSessionDate)
        val layoutExercises: LinearLayout = view.findViewById(R.id.layoutExercises)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_workout_session, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val session = sessions[position]
        holder.tvDate.text = session.date
        holder.layoutExercises.removeAllViews()

        val inflater = LayoutInflater.from(holder.itemView.context)

        session.exercises.forEach { exercise ->
            val exerciseView = inflater.inflate(R.layout.item_session_exercise, holder.layoutExercises, false)
            exerciseView.findViewById<TextView>(R.id.tvExerciseName).text = exercise.exerciseName
            exerciseView.findViewById<TextView>(R.id.tvSetCount).text = "${exercise.sets.size} sets"

            val layoutSets = exerciseView.findViewById<LinearLayout>(R.id.layoutSets)
            exercise.sets.forEach { set ->
                val setView = inflater.inflate(R.layout.item_set_log, layoutSets, false)
                setView.findViewById<TextView>(R.id.tvWeight).text = "${set.weight} kg"
                setView.findViewById<TextView>(R.id.tvReps).text = "${set.reps} reps"
                if (set.isPr) {
                    setView.findViewById<View>(R.id.ivPr).visibility = View.VISIBLE
                }
                layoutSets.addView(setView)
            }
            holder.layoutExercises.addView(exerciseView)
        }
    }

    override fun getItemCount() = sessions.size

    fun updateList(newList: List<WorkoutSession>) {
        sessions = newList
        notifyDataSetChanged()
    }
}
