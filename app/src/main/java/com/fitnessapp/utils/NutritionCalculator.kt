package com.fitnessapp.utils

object NutritionCalculator {
    
    /**
     * Calculate BMR using Mifflin-St Jeor Equation
     * Men: BMR = 10 * weight(kg) + 6.25 * height(cm) - 5 * age + 5
     * Women: BMR = 10 * weight(kg) + 6.25 * height(cm) - 5 * age - 161
     */
    fun calculateBMR(
        weight: Double, // in kg
        height: Double, // in cm
        age: Int,
        isMale: Boolean
    ): Int {
        val bmr = 10 * weight + 6.25 * height - 5 * age
        return if (isMale) {
            (bmr + 5).toInt()
        } else {
            (bmr - 161).toInt()
        }
    }
    
    /**
     * Calculate TDEE (Total Daily Energy Expenditure)
     * BMR multiplied by activity level
     */
    fun calculateTDEE(bmr: Int, activityLevel: ActivityLevel): Int {
        return (bmr * activityLevel.multiplier).toInt()
    }
    
    /**
     * Calculate calorie target based on goal
     */
    fun calculateCalorieTarget(tdee: Int, goal: FitnessGoal): Int {
        return when (goal) {
            FitnessGoal.WEIGHT_LOSS -> (tdee * 0.8).toInt() // 20% deficit
            FitnessGoal.MUSCLE_GAIN -> (tdee * 1.1).toInt() // 10% surplus
            FitnessGoal.MAINTENANCE -> tdee
        }
    }
    
    /**
     * Calculate macro split
     */
    fun calculateMacros(calories: Int, goal: FitnessGoal): Triple<Int, Int, Int> {
        // Returns (protein, carbs, fats) in grams
        return when (goal) {
            FitnessGoal.WEIGHT_LOSS -> {
                val protein = (calories * 0.35 / 4).toInt() // 35% protein
                val fats = (calories * 0.30 / 9).toInt()    // 30% fats
                val carbs = (calories * 0.35 / 4).toInt()   // 35% carbs
                Triple(protein, carbs, fats)
            }
            FitnessGoal.MUSCLE_GAIN -> {
                val protein = (calories * 0.30 / 4).toInt() // 30% protein
                val carbs = (calories * 0.50 / 4).toInt()   // 50% carbs
                val fats = (calories * 0.20 / 9).toInt()    // 20% fats
                Triple(protein, carbs, fats)
            }
            FitnessGoal.MAINTENANCE -> {
                val protein = (calories * 0.25 / 4).toInt() // 25% protein
                val carbs = (calories * 0.50 / 4).toInt()   // 50% carbs
                val fats = (calories * 0.25 / 9).toInt()    // 25% fats
                Triple(protein, carbs, fats)
            }
        }
    }
    
    /**
     * Calculate protein requirement based on weight
     * Returns grams of protein needed per day
     */
    fun calculateProteinRequirement(weightKg: Double, goal: FitnessGoal): Int {
        return when (goal) {
            FitnessGoal.WEIGHT_LOSS -> (weightKg * 2.0).toInt() // 2g per kg
            FitnessGoal.MUSCLE_GAIN -> (weightKg * 2.2).toInt() // 2.2g per kg
            FitnessGoal.MAINTENANCE -> (weightKg * 1.6).toInt() // 1.6g per kg
        }
    }
    
    /**
     * Calculate water intake requirement
     * Returns liters per day
     */
    fun calculateWaterIntake(weightKg: Double): Double {
        return weightKg * 0.033 // 33ml per kg
    }
    
    enum class ActivityLevel(val description: String, val multiplier: Double) {
        SEDENTARY("Little or no exercise", 1.2),
        LIGHTLY_ACTIVE("Exercise 1-3 days/week", 1.375),
        MODERATELY_ACTIVE("Exercise 3-5 days/week", 1.55),
        VERY_ACTIVE("Exercise 6-7 days/week", 1.725),
        EXTREMELY_ACTIVE("Hard exercise daily + physical job", 1.9)
    }
    
    enum class FitnessGoal {
        WEIGHT_LOSS,
        MUSCLE_GAIN,
        MAINTENANCE
    }
}
