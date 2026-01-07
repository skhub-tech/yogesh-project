package com.fitnessapp.ui.exercises

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fitnessapp.R
import com.fitnessapp.data.ExerciseVariationsData
import com.fitnessapp.model.ExerciseVariation
import com.google.android.material.appbar.CollapsingToolbarLayout
import android.widget.Toast
import androidx.navigation.fragment.findNavController

class ExerciseDetailFragment : Fragment(R.layout.fragment_exercise_detail) {

    private lateinit var adapter: ExerciseVariationAdapter
    private var exerciseName: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        exerciseName = arguments?.getString(ARG_EXERCISE_NAME) ?: ""
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Setup toolbar
        val collapsingToolbar = view.findViewById<CollapsingToolbarLayout>(R.id.collapsingToolbar)
        collapsingToolbar.title = exerciseName

        // Setup RecyclerView
        val rvVariations = view.findViewById<RecyclerView>(R.id.rvVariations)
        
        // Get variations for this exercise
        val variations = ExerciseVariationsData.getVariationsForExercise(exerciseName)
        
        if (variations.isEmpty()) {
            Toast.makeText(
                context,
                "No variations available for this exercise yet",
                Toast.LENGTH_SHORT
            ).show()
            parentFragmentManager.popBackStack()
            return
        }

        adapter = ExerciseVariationAdapter(variations) { variation ->
            onVariationSelected(variation)
        }
        
        rvVariations.layoutManager = LinearLayoutManager(context)
        rvVariations.adapter = adapter
    }

    private fun onVariationSelected(variation: ExerciseVariation) {
        // Navigate to detail fragment with variation data
        try {
            val bundle = Bundle().apply {
                putString("exercise_name", exerciseName)
                putString("variation_id", variation.id)
            }
            findNavController().navigate(R.id.variationDetailFragment, bundle)
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(context, "Error opening details", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        private const val ARG_EXERCISE_NAME = "exercise_name"

        fun newInstance(exerciseName: String): ExerciseDetailFragment {
            return ExerciseDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_EXERCISE_NAME, exerciseName)
                }
            }
        }
    }
}
