package com.fitnessapp.ui.diet

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fitnessapp.R
import com.fitnessapp.data.NutritionTipsData
import com.google.android.material.chip.Chip

class TipsTabFragment : Fragment(R.layout.tab_tips) {

    private lateinit var adapter: NutritionTipsAdapter
    private val allTips = NutritionTipsData.getAllTips()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rvTips = view.findViewById<RecyclerView>(R.id.rvTips)

        adapter = NutritionTipsAdapter(allTips)

        rvTips.layoutManager = LinearLayoutManager(context)
        rvTips.adapter = adapter
        
        setupCategoryChips(view)
    }
    
    private fun setupCategoryChips(view: View) {
        view.findViewById<Chip>(R.id.chipAll).setOnClickListener { filterTips("all") }
        view.findViewById<Chip>(R.id.chipProtein).setOnClickListener { filterTips("protein") }
        view.findViewById<Chip>(R.id.chipHydration).setOnClickListener { filterTips("hydration") }
        view.findViewById<Chip>(R.id.chipTiming).setOnClickListener { filterTips("timing") }
        view.findViewById<Chip>(R.id.chipIndian).setOnClickListener { filterTips("indian_diet") }
        view.findViewById<Chip>(R.id.chipWeightLoss).setOnClickListener { filterTips("weight_loss") }
        view.findViewById<Chip>(R.id.chipMuscle).setOnClickListener { filterTips("muscle_gain") }
    }
    
    private fun filterTips(category: String) {
        val filteredTips = if (category == "all") {
            allTips
        } else {
            NutritionTipsData.getTipsByCategory(category)
        }
        adapter.updateTips(filteredTips)
    }
}
