package com.fitnessapp.ui.tracker

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fitnessapp.R
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet

class WorkoutsTabFragment : Fragment(R.layout.tab_workouts) {

    private val viewModel: TrackerViewModel by activityViewModels()
    private lateinit var workoutAdapter: WorkoutSessionAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val chartVolume = view.findViewById<LineChart>(R.id.chartVolume)
        val rvWorkoutHistory = view.findViewById<RecyclerView>(R.id.rvWorkoutHistory)

        // Setup chart
        setupChart(chartVolume)

        // Setup RecyclerView
        workoutAdapter = WorkoutSessionAdapter(emptyList())
        rvWorkoutHistory.layoutManager = LinearLayoutManager(context)
        rvWorkoutHistory.adapter = workoutAdapter

        // Observe volume data
        viewModel.volumeData.observe(viewLifecycleOwner) { data ->
            val entries = data.mapIndexed { index, pair ->
                Entry(index.toFloat(), pair.second.toFloat())
            }
            
            if (entries.isNotEmpty()) {
                val dataSet = LineDataSet(entries, "Training Volume")
                dataSet.color = Color.parseColor("#4CAF50") // Green
                dataSet.setDrawFilled(true)
                dataSet.fillColor = Color.parseColor("#C8E6C9")
                dataSet.lineWidth = 2.5f
                dataSet.setCircleColor(Color.parseColor("#4CAF50"))
                dataSet.circleRadius = 4f
                dataSet.valueTextSize = 10f
                
                chartVolume.data = LineData(dataSet)
                chartVolume.invalidate()
            }
        }

        // Observe workout sessions
        viewModel.sessions.observe(viewLifecycleOwner) { sessions ->
            workoutAdapter.updateList(sessions)
        }
    }

    private fun setupChart(chart: LineChart) {
        chart.description.isEnabled = false
        chart.setTouchEnabled(true)
        chart.isDragEnabled = true
        chart.setScaleEnabled(true)
        chart.setPinchZoom(true)
        chart.setDrawGridBackground(false)
        
        val xAxis = chart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.setDrawGridLines(false)
        xAxis.granularity = 1f
        
        chart.axisRight.isEnabled = false
        chart.axisLeft.setDrawGridLines(true)
        chart.axisLeft.gridColor = Color.parseColor("#E0E0E0")
        
        chart.legend.isEnabled = true
        chart.setExtraOffsets(10f, 10f, 10f, 10f)
    }
}
