package com.fitnessapp.ui.diet

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fitnessapp.R
import com.fitnessapp.model.NutritionTip

class NutritionTipsAdapter(
    private val tips: List<NutritionTip>
) : RecyclerView.Adapter<NutritionTipsAdapter.TipViewHolder>() {

    class TipViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = view.findViewById(R.id.tvTipTitle)
        val tvDescription: TextView = view.findViewById(R.id.tvTipDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TipViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_nutrition_tip, parent, false)
        return TipViewHolder(view)
    }

    override fun onBindViewHolder(holder: TipViewHolder, position: Int) {
        val tip = tips[position]
        holder.tvTitle.text = tip.title
        holder.tvDescription.text = tip.description
    }

    override fun getItemCount() = tips.size
}
