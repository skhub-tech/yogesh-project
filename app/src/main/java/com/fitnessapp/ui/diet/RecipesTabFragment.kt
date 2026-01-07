package com.fitnessapp.ui.diet

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fitnessapp.R
import com.fitnessapp.data.IndianRecipesData
import android.widget.Toast

class RecipesTabFragment : Fragment(R.layout.tab_recipes) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rvRecipes = view.findViewById<RecyclerView>(R.id.rvRecipes)
        val recipes = IndianRecipesData.getAllRecipes()

        val adapter = RecipesAdapter(recipes) { recipe ->
            // Show recipe details (for now, just a toast)
            Toast.makeText(context, "Clicked: ${recipe.name}", Toast.LENGTH_SHORT).show()
            // TODO: Navigate to recipe detail page
        }

        rvRecipes.layoutManager = LinearLayoutManager(context)
        rvRecipes.adapter = adapter
    }
}
