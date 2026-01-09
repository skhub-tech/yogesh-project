package com.fitnessapp.model

data class WorkoutSet(
    val id: String = "",
    val exerciseName: String = "",
    val weight: Double = 0.0,
    val reps: Int = 0,
    val timestamp: Long = System.currentTimeMillis(),
    val userId: String = ""
) {
    // Calculate volume for this set (weight x reps)
    fun getVolume(): Double {
        return weight * reps
    }
    
    // Get formatted display string
    fun getFormattedSet(): String {
        return "$exerciseName: ${String.format("%.1f", weight)}kg × $reps reps"
    }
    
    // Calculate estimated 1RM using Epley formula
    fun getEstimated1RM(): Double {
        if (reps == 1) return weight
        return weight * (1 + reps / 30.0)
    }
}
