package com.fitnessapp.ui.exercises

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fitnessapp.R
import com.fitnessapp.model.Exercise

class ExercisesAdapter(
    private var exercises: List<Exercise>,
    private val onExerciseClick: ((Exercise) -> Unit)? = null
) : RecyclerView.Adapter<ExercisesAdapter.ExerciseViewHolder>() {

    class ExerciseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivExerciseImage: ImageView = view.findViewById(R.id.ivExerciseImage)
        val tvName: TextView = view.findViewById(R.id.tvExerciseName)
        val tvMuscleGroup: TextView = view.findViewById(R.id.tvMuscleGroup)
        val tvDifficulty: TextView = view.findViewById(R.id.tvDifficulty)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_exercise, parent, false)
        return ExerciseViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        val exercise = exercises[position]
        holder.tvName.text = exercise.name
        holder.tvMuscleGroup.text = "Target: ${exercise.muscleGroup}"
        holder.tvDifficulty.text = "Difficulty: ${exercise.difficulty}"
        
        // Set exercise-specific image if available
        if (exercise.imageRes != 0) {
            holder.ivExerciseImage.setImageResource(exercise.imageRes)
        } else {
            holder.ivExerciseImage.setImageResource(R.drawable.ic_fitness)
        }
        
        // Handle item click
        holder.itemView.setOnClickListener {
            onExerciseClick?.invoke(exercise)
        }
    }

    override fun getItemCount() = exercises.size

    fun updateList(newExercises: List<Exercise>) {
        exercises = newExercises
        notifyDataSetChanged()
    }
}
