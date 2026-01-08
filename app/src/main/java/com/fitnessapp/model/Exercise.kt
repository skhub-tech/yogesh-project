package com.fitnessapp.model

data class Exercise(
    val id: String = "",
    val name: String = "",
    val muscleGroup: String = "", // chest, back, legs, arms, shoulders, core, cardio
    val difficulty: String = "beginner", // beginner, intermediate, advanced
    val description: String = "",
    val videoUrl: String = "",
    val caloriesBurnedPerMin: Double = 0.0,
    val imageRes: Int = 0 // Drawable resource ID for exercise image
)
