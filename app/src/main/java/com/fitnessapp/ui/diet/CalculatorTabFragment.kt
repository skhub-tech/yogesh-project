package com.fitnessapp.ui.diet

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.fragment.app.Fragment
import com.fitnessapp.R
import com.fitnessapp.utils.NutritionCalculator
import com.google.android.material.card.MaterialCardView
import kotlin.math.roundToInt

class CalculatorTabFragment : Fragment(R.layout.tab_calculator) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val etAge = view.findViewById<EditText>(R.id.etAge)
        val etWeight = view.findViewById<EditText>(R.id.etWeight)
        val etHeight = view.findViewById<EditText>(R.id.etHeight)
        val rgGender = view.findViewById<RadioGroup>(R.id.rgGender)
        val spinnerActivity = view.findViewById<Spinner>(R.id.spinnerActivity)
        val spinnerGoal = view.findViewById<Spinner>(R.id.spinnerGoal)
        val btnCalculate = view.findViewById<com.google.android.material.button.MaterialButton>(R.id.btnCalculate)
        val cardResults = view.findViewById<MaterialCardView>(R.id.cardResults)

        // Setup spinners
        val activityLevels = arrayOf(
            "Sedentary (Little or no exercise)",
            "Lightly Active (1-3 days/week)",
            "Moderately Active (3-5 days/week)",
            "Very Active (6-7 days/week)",
            "Extremely Active (Daily hard exercise)"
        )
        spinnerActivity.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, activityLevels).apply {
            setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }

        val goals = arrayOf("Weight Loss", "Muscle Gain", "Maintenance")
        spinnerGoal.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, goals).apply {
            setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }

        btnCalculate.setOnClickListener {
            calculateNutrition(view, etAge, etWeight, etHeight, rgGender, spinnerActivity, spinnerGoal, cardResults)
        }
    }

    private fun calculateNutrition(
        view: View,
        etAge: EditText,
        etWeight: EditText,
        etHeight: EditText,
        rgGender: RadioGroup,
        spinnerActivity: Spinner,
        spinnerGoal: Spinner,
        cardResults: MaterialCardView
    ) {
        val ageStr = etAge.text.toString()
        val weightStr = etWeight.text.toString()
        val heightStr = etHeight.text.toString()

        if (ageStr.isBlank() || weightStr.isBlank() || heightStr.isBlank()) {
            Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
            return
        }

        val age = ageStr.toInt()
        val weight = weightStr.toDouble()
        val height = heightStr.toDouble()
        val isMale = rgGender.checkedRadioButtonId == R.id.rbMale

        val activityLevel = when (spinnerActivity.selectedItemPosition) {
            0 -> NutritionCalculator.ActivityLevel.SEDENTARY
            1 -> NutritionCalculator.ActivityLevel.LIGHTLY_ACTIVE
            2 -> NutritionCalculator.ActivityLevel.MODERATELY_ACTIVE
            3 -> NutritionCalculator.ActivityLevel.VERY_ACTIVE
            else -> NutritionCalculator.ActivityLevel.EXTREMELY_ACTIVE
        }

        val goal = when (spinnerGoal.selectedItemPosition) {
            0 -> NutritionCalculator.FitnessGoal.WEIGHT_LOSS
            1 -> NutritionCalculator.FitnessGoal.MUSCLE_GAIN
            else -> NutritionCalculator.FitnessGoal.MAINTENANCE
        }

        // Calculate
        val bmr = NutritionCalculator.calculateBMR(weight, height, age, isMale)
        val tdee = NutritionCalculator.calculateTDEE(bmr, activityLevel)
        val calorieTarget = NutritionCalculator.calculateCalorieTarget(tdee, goal)
        val macros = NutritionCalculator.calculateMacros(calorieTarget, goal)
        val waterIntake = NutritionCalculator.calculateWaterIntake(weight)

        // Display results
        view.findViewById<TextView>(R.id.tvBMR).text = "BMR: $bmr kcal"
        view.findViewById<TextView>(R.id.tvTDEE).text = "TDEE: $tdee kcal"
        view.findViewById<TextView>(R.id.tvCalorieTarget).text = "Daily Calorie Target: $calorieTarget kcal"
        view.findViewById<TextView>(R.id.tvMacros).text = 
            "Protein: ${macros.first}g | Carbs: ${macros.second}g | Fats: ${macros.third}g"
        view.findViewById<TextView>(R.id.tvWater).text = 
            "Water Intake: ${(waterIntake * 10).roundToInt() / 10.0}L/day"

        cardResults.visibility = View.VISIBLE
    }
}
