package com.fitnessapp.ui.exercises

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.fitnessapp.R
import com.fitnessapp.model.ExerciseVariation
import com.google.android.material.button.MaterialButton
import com.google.android.material.chip.Chip
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class ExerciseVariationAdapter(
    private var variations: List<ExerciseVariation>,
    private val onVariationClick: (ExerciseVariation) -> Unit
) : RecyclerView.Adapter<ExerciseVariationAdapter.VariationViewHolder>() {

    class VariationViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvVariationName: TextView = view.findViewById(R.id.tvVariationName)
        val tvTargetMuscles: TextView = view.findViewById(R.id.tvTargetMuscles)
        val tvEquipment: TextView = view.findViewById(R.id.tvEquipment)
        val tvDescription: TextView = view.findViewById(R.id.tvDescription)
        val chipDifficulty: Chip = view.findViewById(R.id.chipDifficulty)
        val btnViewInstructions: MaterialButton = view.findViewById(R.id.btnViewInstructions)
        val vpStepImages: ViewPager2 = view.findViewById(R.id.vpStepImages)
        val tabDots: TabLayout = view.findViewById(R.id.tabDots)
        val ivAnimation: ImageView = view.findViewById(R.id.ivAnimation)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VariationViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_exercise_variation, parent, false)
        return VariationViewHolder(view)
    }

    override fun onBindViewHolder(holder: VariationViewHolder, position: Int) {
        val variation = variations[position]
        
        holder.tvVariationName.text = variation.name
        holder.tvTargetMuscles.text = "Target: ${variation.targetMuscles}"
        holder.tvEquipment.text = "Equipment: ${variation.equipment}"
        holder.tvDescription.text = variation.description
        holder.chipDifficulty.text = variation.difficulty
        
        // Set difficulty color
        val difficultyColor = when (variation.difficulty.lowercase()) {
            "beginner" -> android.graphics.Color.parseColor("#4CAF50") // Green
            "intermediate" -> android.graphics.Color.parseColor("#FF9800") // Orange
            "advanced" -> android.graphics.Color.parseColor("#F44336") // Red
            else -> android.graphics.Color.parseColor("#9E9E9E") // Gray
        }
        holder.chipDifficulty.setChipBackgroundColorResource(android.R.color.transparent)
        holder.chipDifficulty.chipBackgroundColor = android.content.res.ColorStateList.valueOf(difficultyColor)
        
        // HIDE visual content from list - will show in detail page
        holder.vpStepImages.visibility = View.GONE
        holder.tabDots.visibility = View.GONE
        holder.ivAnimation.visibility = View.GONE
        
        // Click handlers - now opens detail page
        holder.itemView.setOnClickListener {
            onVariationClick(variation)
        }
        
        // View instructions button also opens detail page
        holder.btnViewInstructions.setOnClickListener {
            onVariationClick(variation)
        }
    }

    override fun getItemCount() = variations.size

    private fun showInstructions(context: android.content.Context, variation: ExerciseVariation) {
        val instructions = variation.instructions.joinToString("\n\n") { step ->
            "• $step"
        }
        
        androidx.appcompat.app.AlertDialog.Builder(context)
            .setTitle("${variation.name} - Instructions")
            .setMessage(instructions)
            .setPositiveButton("Got it", null)
            .show()
    }

    fun updateList(newVariations: List<ExerciseVariation>) {
        variations = newVariations
        notifyDataSetChanged()
    }
}
