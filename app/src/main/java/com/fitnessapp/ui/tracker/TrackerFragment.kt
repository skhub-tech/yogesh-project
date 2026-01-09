package com.fitnessapp.ui.tracker

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.fitnessapp.R
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText

class TrackerFragment : Fragment(R.layout.fragment_tracker) {

    private val viewModel: TrackerViewModel by viewModels()
    private lateinit var workoutAdapter: WorkoutSessionAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val chartWeight = view.findViewById<LineChart>(R.id.chartWeight)
        val chartVolume = view.findViewById<LineChart>(R.id.chartVolume)
        val etWeight = view.findViewById<TextInputEditText>(R.id.etWeight)
        val btnSaveLog = view.findViewById<Button>(R.id.btnSaveLog)
        val rvWorkoutHistory = view.findViewById<RecyclerView>(R.id.rvWorkoutHistory)

        workoutAdapter = WorkoutSessionAdapter(emptyList())
        rvWorkoutHistory.adapter = workoutAdapter
        
        setupChart(chartWeight)
        setupChart(chartVolume)

        viewModel.sessions.observe(viewLifecycleOwner) { sessions ->
            workoutAdapter.updateList(sessions)
        }

        viewModel.volumeData.observe(viewLifecycleOwner) { data ->
            val entries = data.mapIndexed { index, pair ->
                Entry(index.toFloat(), pair.second.toFloat())
            }
            if (entries.isNotEmpty()) {
                val dataSet = LineDataSet(entries, "Total Volume")
                dataSet.color = Color.parseColor("#388E3C") // Green
                dataSet.setDrawFilled(true)
                dataSet.fillColor = Color.parseColor("#C8E6C9")
                chartVolume.data = LineData(dataSet)
                chartVolume.invalidate()
            }
        }

        viewModel.logs.observe(viewLifecycleOwner) { logs ->
            val entries = logs.mapIndexed { index, log ->
                Entry(index.toFloat(), log.weight.toFloat())
            }
            if (entries.isNotEmpty()) {
                val dataSet = LineDataSet(entries, "Weight History")
                dataSet.color = Color.BLUE
                dataSet.valueTextColor = Color.BLACK
                chartWeight.data = LineData(dataSet)
                chartWeight.invalidate()
            }
        }

        viewModel.saveStatus.observe(viewLifecycleOwner) { result ->
            if (result.isSuccess) {
                Toast.makeText(context, "Log Saved!", Toast.LENGTH_SHORT).show()
                etWeight.text?.clear()
            } else {
                Toast.makeText(context, "Error: ${result.exceptionOrNull()?.message}", Toast.LENGTH_SHORT).show()
            }
        }

        btnSaveLog.setOnClickListener {
            val weightStr = etWeight.text.toString()
            if (weightStr.isNotEmpty()) {
                 viewModel.saveLog(weightStr.toDouble())
            }
        }
        
        view.findViewById<Button>(R.id.btnLogSet).setOnClickListener {
            showLogSetDialog()
        }

        view.findViewById<Button>(R.id.btnViewPRs).setOnClickListener {
            findNavController().navigate(R.id.personalRecordsFragment)
        }

        view.findViewById<Button>(R.id.btnExportData).setOnClickListener {
            val csv = viewModel.getCsvContent()
            val intent = android.content.Intent(android.content.Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(android.content.Intent.EXTRA_SUBJECT, "My Workout Data")
                putExtra(android.content.Intent.EXTRA_TEXT, csv)
            }
            startActivity(android.content.Intent.createChooser(intent, "Share CSV Data"))
        }
    }
    
    private fun showLogSetDialog() {
        val context = requireContext()
        val layout = android.widget.LinearLayout(context).apply {
            orientation = android.widget.LinearLayout.VERTICAL
            setPadding(50, 40, 50, 10)
        }

        val etExercise = android.widget.EditText(context).apply { hint = "Exercise Name (e.g. Squat)" }
        val etWeight = android.widget.EditText(context).apply { 
            hint = "Weight (kg)"
            inputType = android.text.InputType.TYPE_CLASS_NUMBER or android.text.InputType.TYPE_NUMBER_FLAG_DECIMAL
        }
        val etReps = android.widget.EditText(context).apply { 
            hint = "Reps"
            inputType = android.text.InputType.TYPE_CLASS_NUMBER
        }

        layout.addView(etExercise)
        layout.addView(etWeight)
        layout.addView(etReps)

        androidx.appcompat.app.AlertDialog.Builder(context)
            .setTitle("Log Workout Set")
            .setView(layout)
            .setPositiveButton("Log") { _, _ ->
                val ex = etExercise.text.toString()
                val w = etWeight.text.toString().toDoubleOrNull()
                val r = etReps.text.toString().toIntOrNull()
                
                if (ex.isNotBlank() && w != null && r != null) {
                    viewModel.logSet(ex, w, r)
                    Toast.makeText(context, "Set Logged!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Invalid Input", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun setupChart(chart: LineChart) {
        chart.description.isEnabled = false
        chart.setTouchEnabled(true)
        chart.setPinchZoom(true)
        
        val xAxis = chart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.setDrawGridLines(false)
        
        chart.axisRight.isEnabled = false
    }
}

