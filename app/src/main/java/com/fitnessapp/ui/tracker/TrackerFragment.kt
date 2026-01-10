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
        
        val tvTotalWorkouts = view.findViewById<android.widget.TextView>(R.id.tvTotalWorkouts)
        val tvMaxVolume = view.findViewById<android.widget.TextView>(R.id.tvMaxVolume)

        workoutAdapter = WorkoutSessionAdapter(emptyList())
        rvWorkoutHistory.adapter = workoutAdapter
        
        setupChart(chartWeight)
        setupChart(chartVolume)

        viewModel.sessions.observe(viewLifecycleOwner) { sessions ->
            workoutAdapter.updateList(sessions)
        }
        
        viewModel.totalWorkouts.observe(viewLifecycleOwner) { count ->
            tvTotalWorkouts.text = count.toString()
        }
        
        viewModel.maxVolume.observe(viewLifecycleOwner) { volume ->
            tvMaxVolume.text = "${volume.toInt()} kg"
        }

        viewModel.volumeData.observe(viewLifecycleOwner) { data ->
            // data is Pair(DateString, Volume)
            val entries = data.mapIndexed { index, pair ->
                Entry(index.toFloat(), pair.second.toFloat())
            }
            val dates = data.map { it.first } // Extract dates for X-Axis

            if (entries.isNotEmpty()) {
                val dataSet = LineDataSet(entries, "Volume")
                dataSet.color = Color.parseColor("#00B0FF") // Blue
                dataSet.setDrawFilled(true)
                dataSet.fillColor = Color.parseColor("#E1F5FE")
                dataSet.mode = LineDataSet.Mode.CUBIC_BEZIER
                dataSet.setDrawValues(false)
                dataSet.setDrawCircles(true)
                dataSet.circleRadius = 4f
                dataSet.setCircleColor(Color.parseColor("#00B0FF"))
                
                chartVolume.xAxis.valueFormatter = com.github.mikephil.charting.formatter.IndexAxisValueFormatter(dates)
                chartVolume.xAxis.labelCount = dates.size.coerceAtMost(5)
                
                chartVolume.data = LineData(dataSet)
                chartVolume.invalidate()
            }
        }

        viewModel.logs.observe(viewLifecycleOwner) { logs ->
            // logs is List<DailyLog> sorted by date
            val entries = logs.mapIndexed { index, log ->
                Entry(index.toFloat(), log.weight.toFloat())
            }
            val dates = logs.map { it.date }

            if (entries.isNotEmpty()) {
                val dataSet = LineDataSet(entries, "Weight")
                dataSet.color = Color.parseColor("#FF9100") // Orange
                dataSet.lineWidth = 2f
                dataSet.setDrawCircles(true)
                dataSet.setCircleColor(Color.parseColor("#FF9100"))
                dataSet.circleRadius = 4f
                dataSet.valueTextSize = 10f
                dataSet.valueTextColor = Color.GRAY
                dataSet.mode = LineDataSet.Mode.HORIZONTAL_BEZIER
                
                chartWeight.xAxis.valueFormatter = com.github.mikephil.charting.formatter.IndexAxisValueFormatter(dates)
                chartWeight.xAxis.labelCount = dates.size.coerceAtMost(5)

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
        chart.legend.isEnabled = false
        
        val xAxis = chart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.setDrawGridLines(false)
        xAxis.textColor = Color.GRAY
        xAxis.granularity = 1f
        
        chart.axisRight.isEnabled = false
        chart.axisLeft.textColor = Color.GRAY
        chart.axisLeft.setDrawGridLines(true)
        chart.axisLeft.gridColor = Color.parseColor("#E0E0E0")
        
        // Add extra offset specifically for bottom to prevent label clipping
        chart.extraBottomOffset = 10f
    }
}

