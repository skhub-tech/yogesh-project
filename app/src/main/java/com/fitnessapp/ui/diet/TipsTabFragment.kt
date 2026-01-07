package com.fitnessapp.ui.diet

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fitnessapp.R
import com.fitnessapp.data.NutritionTipsData

class TipsTabFragment : Fragment(R.layout.tab_tips) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rvTips = view.findViewById<RecyclerView>(R.id.rvTips)
        val tips = NutritionTipsData.getAllTips()

        val adapter = NutritionTipsAdapter(tips)

        rvTips.layoutManager = LinearLayoutManager(context)
        rvTips.adapter = adapter
    }
}
