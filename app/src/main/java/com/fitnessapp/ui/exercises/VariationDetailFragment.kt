package com.fitnessapp.ui.exercises

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.fitnessapp.R
import com.fitnessapp.model.ExerciseVariation
import com.fitnessapp.data.ExerciseVariationsData
import com.google.android.material.chip.Chip
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.button.MaterialButton

class VariationDetailFragment : Fragment(R.layout.fragment_variation_detail) {

    private var exerciseName: String = ""
    private var variationId: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        exerciseName = arguments?.getString(ARG_EXERCISE_NAME) ?: ""
        variationId = arguments?.getString(ARG_VARIATION_ID) ?: ""
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Get the actual variation from data
        val variations = ExerciseVariationsData.getVariationsForExercise(exerciseName)
        val variation = variations.find { it.id == variationId }

        if (variation != null) {
            displayVariation(view, variation)
        } else {
            Toast.makeText(context, "Variation not found", Toast.LENGTH_SHORT).show()
            parentFragmentManager.popBackStack()
        }
    }

    private fun displayVariation(view: View, variation: ExerciseVariation) {
        // Set text content
        view.findViewById<TextView>(R.id.tvVariationName).text = variation.name
        view.findViewById<TextView>(R.id.tvDescription).text = variation.description
        view.findViewById<TextView>(R.id.tvTargetMuscles).text = variation.targetMuscles
        view.findViewById<TextView>(R.id.tvEquipment).text = "Equipment: ${variation.equipment}"

        // Set difficulty chip
        val chipDifficulty = view.findViewById<Chip>(R.id.chipDifficulty)
        chipDifficulty.text = variation.difficulty
        val difficultyColor = when (variation.difficulty.lowercase()) {
            "beginner" -> android.graphics.Color.parseColor("#4CAF50")
            "intermediate" -> android.graphics.Color.parseColor("#FF9800")
            "advanced" -> android.graphics.Color.parseColor("#F44336")
            else -> android.graphics.Color.parseColor("#9E9E9E")
        }
        chipDifficulty.chipBackgroundColor = android.content.res.ColorStateList.valueOf(difficultyColor)

        // Display step images if available
        val vpStepImages = view.findViewById<ViewPager2>(R.id.vpStepImages)
        val tabDots = view.findViewById<TabLayout>(R.id.tabDots)
        
        if (variation.stepImages.isNotEmpty()) {
            vpStepImages.visibility = View.VISIBLE
            tabDots.visibility = View.VISIBLE
            
            val imagesAdapter = StepImagesAdapter(variation.stepImages)
            vpStepImages.adapter = imagesAdapter
            
            TabLayoutMediator(tabDots, vpStepImages) { _, _ -> }.attach()
        }

        // Hide GIF (not using GIFs anymore)
        view.findViewById<ImageView>(R.id.ivAnimation).visibility = View.GONE

        // Display instructions
        val rvInstructions = view.findViewById<RecyclerView>(R.id.rvInstructions)
        rvInstructions.layoutManager = LinearLayoutManager(context)
        rvInstructions.adapter = InstructionsAdapter(variation.instructions)

        // Add to workout button
        view.findViewById<MaterialButton>(R.id.btnAddToWorkout).setOnClickListener {
            Toast.makeText(context, "Added ${variation.name} to workout!", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        private const val ARG_EXERCISE_NAME = "exercise_name"
        private const val ARG_VARIATION_ID = "variation_id"

        fun newInstance(exerciseName: String, variationId: String): VariationDetailFragment {
            return VariationDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_EXERCISE_NAME, exerciseName)
                    putString(ARG_VARIATION_ID, variationId)
                }
            }
        }
    }
}
