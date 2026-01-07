package com.fitnessapp.ui.exercises

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.ProgressBar
import androidx.navigation.fragment.findNavController
import androidx.core.os.bundleOf
import com.fitnessapp.R

class ExercisesFragment : Fragment(R.layout.fragment_exercise_list) {

    private val viewModel: ExercisesViewModel by viewModels()
    private lateinit var adapter: ExercisesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val progressBar = view.findViewById<ProgressBar>(R.id.progressBar)
        val rvExercises = view.findViewById<RecyclerView>(R.id.rvExercises)

        adapter = ExercisesAdapter(emptyList()) { exercise ->
            // Navigate to exercise detail page
            navigateToExerciseDetail(exercise.name)
        }
        rvExercises.layoutManager = LinearLayoutManager(context)
        rvExercises.adapter = adapter

        viewModel.exercises.observe(viewLifecycleOwner) { list ->
            adapter.updateList(list)
        }

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            progressBar.isVisible = isLoading
        }
        
        view.findViewById<com.google.android.material.floatingactionbutton.FloatingActionButton>(R.id.fabAddExercise).setOnClickListener {
            showAddExerciseDialog()
        }
    }
    
    private fun navigateToExerciseDetail(exerciseName: String) {
        try {
            val bundle = bundleOf("exercise_name" to exerciseName)
            findNavController().navigate(R.id.exerciseDetailFragment, bundle)
        } catch (e: Exception) {
            e.printStackTrace()
            android.widget.Toast.makeText(context, "Error opening exercise details", android.widget.Toast.LENGTH_SHORT).show()
        }
    }
    
    private fun showAddExerciseDialog() {
        val context = requireContext()
        val layout = android.widget.LinearLayout(context).apply {
            orientation = android.widget.LinearLayout.VERTICAL
            setPadding(50, 40, 50, 10)
        }

        val etName = android.widget.EditText(context).apply {
            hint = "Exercise Name"
        }
        val etMuscle = android.widget.EditText(context).apply {
            hint = "Muscle Group (e.g. Chest)"
        }
        
        layout.addView(etName)
        layout.addView(etMuscle)

        androidx.appcompat.app.AlertDialog.Builder(context)
            .setTitle("Add Custom Exercise")
            .setView(layout)
            .setPositiveButton("Add") { _, _ ->
                val name = etName.text.toString()
                val muscle = etMuscle.text.toString()
                if (name.isNotBlank() && muscle.isNotBlank()) {
                    val newExercise = com.fitnessapp.model.Exercise(
                        id = java.util.UUID.randomUUID().toString(),
                        name = name,
                        muscleGroup = muscle,
                        difficulty = "Custom"
                    )
                    viewModel.addExercise(newExercise)
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
}
