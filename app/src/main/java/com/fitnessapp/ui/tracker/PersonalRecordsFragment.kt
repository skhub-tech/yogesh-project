package com.fitnessapp.ui.tracker

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fitnessapp.R

class PersonalRecordsFragment : Fragment(R.layout.fragment_personal_records) {

    private val viewModel: PersonalRecordsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rvTracker = view.findViewById<RecyclerView>(R.id.rvPersonalRecords)
        val adapter = PRAdapter(emptyList())
        rvTracker.layoutManager = LinearLayoutManager(context)
        rvTracker.adapter = adapter

        viewModel.records.observe(viewLifecycleOwner) { records ->
            adapter.updateList(records)
        }
    }

    class PRAdapter(private var records: List<PersonalRecord>) : RecyclerView.Adapter<PRAdapter.ViewHolder>() {
        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val tvName: TextView = view.findViewById(R.id.tvPrExerciseName)
            val tvWeight: TextView = view.findViewById(R.id.tvPrWeight)
            val tvReps: TextView = view.findViewById(R.id.tvPrReps)
            val tv1RM: TextView = view.findViewById(R.id.tvPr1RM)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = android.view.LayoutInflater.from(parent.context).inflate(R.layout.item_personal_record, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val record = records[position]
            holder.tvName.text = record.exerciseName
            holder.tvWeight.text = "${String.format("%.1f", record.bestWeight)} kg"
            holder.tvReps.text = "${record.bestReps} reps"
            holder.tv1RM.text = "${String.format("%.1f", record.estimated1RM)} kg"
        }

        override fun getItemCount() = records.size

        fun updateList(newList: List<PersonalRecord>) {
            records = newList
            notifyDataSetChanged()
        }
    }
}
