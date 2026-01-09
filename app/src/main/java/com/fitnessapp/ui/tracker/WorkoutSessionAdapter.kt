package com.fitnessapp.ui.tracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fitnessapp.R
import com.fitnessapp.model.WorkoutSession
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class WorkoutSessionAdapter(private var sessions: List<WorkoutSession>) :
    RecyclerView.Adapter<WorkoutSessionAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvExerciseName: TextView = view.findViewById(R.id.tvExerciseName)
        val tvSetDetails: TextView = view.findViewById(R.id.tvSetDetails)
        val tvTimestamp: TextView = view.findViewById(R.id.tvTimestamp)
        val tvVolume: TextView = view.findViewById(R.id.tvVolume)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_workout_session, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val session = sessions[position]
        
        // Display workout session name or first exercise
        val exerciseName = if (session.exercises.isNotEmpty()) {
            session.exercises.first().exerciseName
        } else {
            "Workout Session"
        }
        holder.tvExerciseName.text = exerciseName
        
        // Display total exercises and sets
        val totalSets = session.exercises.sumOf { it.sets.size }
        holder.tvSetDetails.text = "${session.exercises.size} exercises • $totalSets sets"
        
        // Display formatted date
        holder.tvTimestamp.text = formatDate(session.date)
        
        // Calculate and display total volume
        val totalVolume = session.exercises.sumOf { exercise ->
            exercise.sets.sumOf { it.weight * it.reps }
        }
        holder.tvVolume.text = totalVolume.toInt().toString()
    }

    override fun getItemCount() = sessions.size

    fun updateList(newList: List<WorkoutSession>) {
        sessions = newList
        notifyDataSetChanged()
    }
    
    private fun formatDate(dateString: String): String {
        return try {
            // Assuming date is stored as timestamp or formatted string
            dateString
        } catch (e: Exception) {
            "Recent"
        }
    }
}
