package com.fitnessapp.model

data class ExerciseSetLog(
    val id: String = "",
    val userId: String = "",
    val exerciseId: String = "",
    val exerciseName: String = "",
    val weight: Double = 0.0,
    val reps: Int = 0,
    val rpe: Double? = null, // Rate of Perceived Exertion
    val comment: String = "",
    val isPr: Boolean = false,
    val timestamp: Long = System.currentTimeMillis()
)
