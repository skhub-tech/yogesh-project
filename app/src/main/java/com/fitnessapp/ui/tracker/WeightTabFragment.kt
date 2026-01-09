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

class WeightTabFragment : Fragment(R.layout.tab_weight) {

    private val viewModel: TrackerViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val chartWeight = view.findViewById<LineChart>(R.id.chartWeight)
        val rvWeightLogs = view.findViewById<RecyclerView>(R.id.rvWeightLogs)

        // Setup chart
        setupChart(chartWeight)

        // Setup RecyclerView (you can add adapter later)
        rvWeightLogs.layoutManager = LinearLayoutManager(context)

        // Observe weight data
        viewModel.logs.observe(viewLifecycleOwner) { logs ->
            val entries = logs.mapIndexed { index, log ->
                Entry(index.toFloat(), log.weight.toFloat())
            }
            
            if (entries.isNotEmpty()) {
                val dataSet = LineDataSet(entries, "Weight Progress")
                dataSet.color = Color.parseColor("#2196F3") // Blue
                dataSet.setDrawFilled(true)
                dataSet.fillColor = Color.parseColor("#BBDEFB")
                dataSet.lineWidth = 2.5f
                dataSet.setCircleColor(Color.parseColor("#2196F3"))
                dataSet.circleRadius = 4f
                dataSet.valueTextSize = 10f
                
                chartWeight.data = LineData(dataSet)
                chartWeight.invalidate()
            }
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
