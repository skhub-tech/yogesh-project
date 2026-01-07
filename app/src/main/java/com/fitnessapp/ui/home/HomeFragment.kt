package com.fitnessapp.ui.home

import android.content.Context
import android.graphics.Typeface
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.fitnessapp.R

class HomeFragment : Fragment(R.layout.fragment_home), SensorEventListener {
    
    private var sensorManager: SensorManager? = null
    private var stepSensor: Sensor? = null
    private var isSensorPresent = false
    
    // View References (will be initialized in bindViews if I had binding, but using findViewById)
    private lateinit var tvSteps: TextView
    private lateinit var tvWater: TextView
    private lateinit var tvBMI: TextView
    private lateinit var tvWorkoutTitle: TextView
    private lateinit var tvWorkoutFocus: TextView
    private lateinit var layoutExercises: LinearLayout
    
    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            // Permission result
        }

    private val viewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tvUserName = view.findViewById<TextView>(R.id.tvUserName)
        tvWorkoutTitle = view.findViewById(R.id.tvWorkoutTitle)
        tvWorkoutFocus = view.findViewById(R.id.tvWorkoutFocus)
        layoutExercises = view.findViewById(R.id.layoutExercises)
        
        // These might not exist yet in XML, need to update XML next
        // But let's assume I WILL add them. For now, I'll use placeholders or find them safely if I update XML first.
        // Actually best to update XML first, but I can write code assuming IDs.
        // IDs to add: tvStepsValue, tvWaterValue, tvBMIValue, btnAddWater
        
        // Sensor Init
        sensorManager = requireContext().getSystemService(Context.SENSOR_SERVICE) as SensorManager
        if (sensorManager?.getDefaultSensor(Sensor.TYPE_STEP_COUNTER) != null) {
            stepSensor = sensorManager?.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)
            isSensorPresent = true
        } else {
             isSensorPresent = false
        }
        
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
            requestPermissionLauncher.launch(android.Manifest.permission.ACTIVITY_RECOGNITION)
        }

        viewModel.user.observe(viewLifecycleOwner) { user ->
            if (user != null) {
                tvUserName.text = "Hi, ${user.name}!"
            }
        }
        
        viewModel.steps.observe(viewLifecycleOwner) { steps ->
            // Check if view exists (will update XML next)
             view.findViewById<TextView>(R.id.tvStepsValue)?.text = "$steps"
        }
        
        viewModel.waterIntake.observe(viewLifecycleOwner) { glasses ->
             view.findViewById<TextView>(R.id.tvWaterValue)?.text = "$glasses / 15"
        }
        
        viewModel.bmi.observe(viewLifecycleOwner) { bmi ->
             view.findViewById<TextView>(R.id.tvBMIValue)?.text = "$bmi"
        }

        viewModel.todaysWorkout.observe(viewLifecycleOwner) { workout ->
            if (workout != null) {
                tvWorkoutTitle.text = workout.dayName
                if (workout.isRestDay) {
                    tvWorkoutFocus.text = "Rest & Recovery"
                    layoutExercises.removeAllViews()
                    addExerciseView(layoutExercises, "Rest Day", "Take it easy today!", "")
                } else {
                    tvWorkoutFocus.text = workout.focus
                    layoutExercises.removeAllViews()
                    workout.exercises.forEach { exercise ->
                        addExerciseView(layoutExercises, exercise.name, "${exercise.sets} sets x ${exercise.reps}", exercise.description)
                    }
                }
            } else {
                tvWorkoutTitle.text = "No Plan"
                tvWorkoutFocus.text = "Complete your profile"
            }
        }
        
        // Water Click Listener
        view.findViewById<View>(R.id.cardWater)?.setOnClickListener {
            viewModel.addWater()
        }
    }
    
    override fun onResume() {
        super.onResume()
        if (isSensorPresent) {
            sensorManager?.registerListener(this, stepSensor, SensorManager.SENSOR_DELAY_UI)
        }
    }

    override fun onPause() {
        super.onPause()
        if (isSensorPresent) {
            sensorManager?.unregisterListener(this)
        }
        viewModel.saveData()
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event != null && event.sensor.type == Sensor.TYPE_STEP_COUNTER) {
            val steps = event.values[0].toInt()
            viewModel.updateSteps(steps)
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}

    private fun addExerciseView(parent: LinearLayout, name: String, details: String, desc: String) {
        val container = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(0, 0, 0, 40)
            }
        }

        val tvName = TextView(context).apply {
            text = name
            textSize = 18f
            setTypeface(null, Typeface.BOLD)
            setTextColor(ContextCompat.getColor(context, android.R.color.black))
        }

        val tvDetails = TextView(context).apply {
            text = details
            textSize = 14f
            setTextColor(ContextCompat.getColor(context, android.R.color.darker_gray))
        }

        container.addView(tvName)
        container.addView(tvDetails)
        parent.addView(container)
    }
}
