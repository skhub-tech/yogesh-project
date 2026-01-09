package com.fitnessapp.ui.tracker

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fitnessapp.R
import com.fitnessapp.model.PersonalRecord
import com.google.android.material.textfield.TextInputEditText

class RecordsTabFragment : Fragment(R.layout.tab_records) {

    private val viewModel: PersonalRecordsViewModel by activityViewModels()
    private lateinit var prAdapter: PersonalRecordsFragment.PRAdapter
    private var allRecords = listOf<PersonalRecord>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rvPersonalRecords = view.findViewById<RecyclerView>(R.id.rvPersonalRecords)
        val tvTotalPRs = view.findViewById<TextView>(R.id.tvTotalPRs)
        val etSearchPR = view.findViewById<TextInputEditText>(R.id.etSearchPR)

        // Setup RecyclerView
        prAdapter = PersonalRecordsFragment.PRAdapter(emptyList())
        rvPersonalRecords.layoutManager = LinearLayoutManager(context)
        rvPersonalRecords.adapter = prAdapter

        // Observe personal records
        viewModel.records.observe(viewLifecycleOwner) { records ->
            allRecords = records
            prAdapter.updateList(records)
            tvTotalPRs.text = "${records.size} PRs"
        }

        // Search functionality
        etSearchPR.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            
            override fun afterTextChanged(s: Editable?) {
                val query = s.toString().trim().lowercase()
                if (query.isEmpty()) {
                    prAdapter.updateList(allRecords)
                } else {
                    val filtered = allRecords.filter { 
                        it.exerciseName.lowercase().contains(query)
                    }
                    prAdapter.updateList(filtered)
                }
            }
        })
    }
}
