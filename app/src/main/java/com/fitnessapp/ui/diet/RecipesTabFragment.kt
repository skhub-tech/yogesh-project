package com.fitnessapp.ui.diet

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fitnessapp.R
import com.fitnessapp.data.IndianRecipesData
import android.widget.Toast
import com.google.android.material.chip.Chip

class RecipesTabFragment : Fragment(R.layout.tab_recipes) {

    private lateinit var adapter: RecipesAdapter
    private val allRecipes = IndianRecipesData.getAllRecipes()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rvRecipes = view.findViewById<RecyclerView>(R.id.rvRecipes)
        
        // Setup adapter
        adapter = RecipesAdapter(allRecipes) { recipe ->
            // Show recipe details (for now, just a toast)
            Toast.makeText(context, "Clicked: ${recipe.name}", Toast.LENGTH_SHORT).show()
            // TODO: Navigate to recipe detail page
        }

        rvRecipes.layoutManager = LinearLayoutManager(context)
        rvRecipes.adapter = adapter
        
        // Setup category chips
        setupCategoryChips(view)
    }
    
    private fun setupCategoryChips(view: View) {
        val chipAll = view.findViewById<Chip>(R.id.chipAll)
        val chipBreakfast = view.findViewById<Chip>(R.id.chipBreakfast)
        val chipLunch = view.findViewById<Chip>(R.id.chipLunch)
        val chipDinner = view.findViewById<Chip>(R.id.chipDinner)
        val chipSnacks = view.findViewById<Chip>(R.id.chipSnacks)
        
        chipAll.setOnClickListener { filterRecipes("all") }
        chipBreakfast.setOnClickListener { filterRecipes("breakfast") }
        chipLunch.setOnClickListener { filterRecipes("lunch") }
        chipDinner.setOnClickListener { filterRecipes("dinner") }
        chipSnacks.setOnClickListener { filterRecipes("snack") }
    }
    
    private fun filterRecipes(category: String) {
        val filteredRecipes = when (category) {
            "all" -> allRecipes
            "dinner" -> IndianRecipesData.getRecipesByCategory("lunch") // Lunch and dinner recipes are same
            else -> IndianRecipesData.getRecipesByCategory(category)
        }
        adapter.updateRecipes(filteredRecipes)
    }
}
