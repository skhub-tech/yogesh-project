package com.fitnessapp.ui.diet

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fitnessapp.R
import com.fitnessapp.model.Recipe
import com.google.android.material.chip.Chip

class RecipesAdapter(
    private val recipes: List<Recipe>,
    private val onRecipeClick: (Recipe) -> Unit
) : RecyclerView.Adapter<RecipesAdapter.RecipeViewHolder>() {

    class RecipeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName: TextView = view.findViewById(R.id.tvRecipeName)
        val tvDescription: TextView = view.findViewById(R.id.tvRecipeDescription)
        val chipDietType: Chip = view.findViewById(R.id.chipDietType)
        val chipDifficulty: Chip = view.findViewById(R.id.chipDifficulty)
        val tvCalories: TextView = view.findViewById(R.id.tvCalories)
        val tvProtein: TextView = view.findViewById(R.id.tvProtein)
        val tvCookTime: TextView = view.findViewById(R.id.tvCookTime)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recipe, parent, false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = recipes[position]
        
        holder.tvName.text = recipe.name
        holder.tvDescription.text = recipe.description
        holder.chipDietType.text = if (recipe.dietType == "veg") "Veg" else "Non-Veg"
        holder.chipDifficulty.text = recipe.difficulty.replaceFirstChar { it.uppercase() }
        holder.tvCalories.text = recipe.calories.toString()
        holder.tvProtein.text = "${recipe.protein}g"
        holder.tvCookTime.text = "${recipe.cookTime} min"
        
        // Set chip colors
        val vegColor = android.graphics.Color.parseColor("#4CAF50")
        val nonVegColor = android.graphics.Color.parseColor("#F44336")
        holder.chipDietType.chipBackgroundColor = android.content.res.ColorStateList.valueOf(
            if (recipe.dietType == "veg") vegColor else nonVegColor
        )
        
        holder.itemView.setOnClickListener {
            onRecipeClick(recipe)
        }
    }

    override fun getItemCount() = recipes.size
}
