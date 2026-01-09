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
        val activityDropdown = view.findViewById<AutoCompleteTextView>(R.id.activityDropdown)
        val goalDropdown = view.findViewById<AutoCompleteTextView>(R.id.goalDropdown)
        val btnCalculate = view.findViewById<com.google.android.material.button.MaterialButton>(R.id.btnCalculate)
        val cardResults = view.findViewById<MaterialCardView>(R.id.cardResults)

        // Setup Activity Level Dropdown
        val activityLevels = arrayOf(
            "🪑 Sedentary (Little or no exercise)",
            "🚶 Light (Exercise 1-3 times/week)",
            "🏃 Moderate (Exercise 4-5 times/week)",
            "💪 Active (Daily exercise or intense 3-4 times/week)",
            "🔥 Very Active (Intense exercise 6-7 times/week)"
        )
        val activityAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, activityLevels)
        activityDropdown.setAdapter(activityAdapter)
        activityDropdown.setText(activityLevels[0], false)

        // Setup Goal Dropdown
        val goals = arrayOf(
            "📉 Weight Loss",
            "⚖️ Maintain Weight",
            "💪 Muscle Gain"
        )
        val goalAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, goals)
        goalDropdown.setAdapter(goalAdapter)
        goalDropdown.setText(goals[0], false)

        btnCalculate.setOnClickListener {
            calculateNutrition(view, etAge, etWeight, etHeight, rgGender, activityDropdown, goalDropdown, cardResults)
        }
    }

    private fun calculateNutrition(
        view: View,
        etAge: EditText,
        etWeight: EditText,
        etHeight: EditText,
        rgGender: RadioGroup,
        activityDropdown: AutoCompleteTextView,
        goalDropdown: AutoCompleteTextView,
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

        // Get activity level from dropdown selection
        val activityLevel = when {
            activityDropdown.text.toString().startsWith("🪑") -> NutritionCalculator.ActivityLevel.SEDENTARY
            activityDropdown.text.toString().startsWith("🚶") -> NutritionCalculator.ActivityLevel.LIGHTLY_ACTIVE
            activityDropdown.text.toString().startsWith("🏃") -> NutritionCalculator.ActivityLevel.MODERATELY_ACTIVE
            activityDropdown.text.toString().startsWith("💪") -> NutritionCalculator.ActivityLevel.VERY_ACTIVE
            activityDropdown.text.toString().startsWith("🔥") -> NutritionCalculator.ActivityLevel.EXTREMELY_ACTIVE
            else -> NutritionCalculator.ActivityLevel.SEDENTARY
        }

        // Get goal from dropdown selection
        val goal = when {
            goalDropdown.text.toString().startsWith("📉") -> NutritionCalculator.FitnessGoal.WEIGHT_LOSS
            goalDropdown.text.toString().startsWith("⚖️") -> NutritionCalculator.FitnessGoal.MAINTENANCE
            goalDropdown.text.toString().startsWith("💪") -> NutritionCalculator.FitnessGoal.MUSCLE_GAIN
            else -> NutritionCalculator.FitnessGoal.WEIGHT_LOSS
        }

        // Calculate
        val bmr = NutritionCalculator.calculateBMR(weight, height, age, isMale)
        val tdee = NutritionCalculator.calculateTDEE(bmr, activityLevel)
        val calorieTarget = NutritionCalculator.calculateCalorieTarget(tdee, goal)
        val macros = NutritionCalculator.calculateMacros(calorieTarget, goal)
        val waterIntake = NutritionCalculator.calculateWaterIntake(weight)

        // Display results with simple formatting
        view.findViewById<TextView>(R.id.tvBMR).text = "${bmr.toInt()} kcal"
        view.findViewById<TextView>(R.id.tvTDEE).text = "${tdee.toInt()} kcal"
        view.findViewById<TextView>(R.id.tvCalorieTarget).text = "${calorieTarget.toInt()} kcal"
        view.findViewById<TextView>(R.id.tvMacros).text = 
            "Protein: ${macros.first}g | Carbs: ${macros.second}g | Fats: ${macros.third}g"
        view.findViewById<TextView>(R.id.tvWater).text = 
            "${(waterIntake * 10).roundToInt() / 10.0}L/day"

        cardResults.visibility = View.VISIBLE
    }
}
