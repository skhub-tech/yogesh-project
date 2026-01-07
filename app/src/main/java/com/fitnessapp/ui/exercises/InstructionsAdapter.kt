package com.fitnessapp.ui.exercises

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fitnessapp.R

class InstructionsAdapter(private val instructions: List<String>) :
    RecyclerView.Adapter<InstructionsAdapter.InstructionViewHolder>() {

    class InstructionViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvStepNumber: TextView = view.findViewById(R.id.tvStepNumber)
        val tvInstructionText: TextView = view.findViewById(R.id.tvInstructionText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InstructionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_instruction, parent, false)
        return InstructionViewHolder(view)
    }

    override fun onBindViewHolder(holder: InstructionViewHolder, position: Int) {
        holder.tvStepNumber.text = "${position + 1}"
        holder.tvInstructionText.text = instructions[position]
    }

    override fun getItemCount() = instructions.size
}
