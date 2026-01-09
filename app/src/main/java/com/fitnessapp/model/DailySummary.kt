package com.fitnessapp.model

data class DailySummary(
    val date: String = "", // Format: "2026-01-09"
    val timestamp: Long = System.currentTimeMillis(),
    val workoutCount: Int = 0,
    val totalSets: Int = 0,
    val totalVolume: Double = 0.0,
    val exercisesPerformed: List<String> = emptyList(),
    val weight: Double = 0.0,
    val notes: String = ""
) {
    // Get formatted volume
    fun getFormattedVolume(): String {
        return if (totalVolume >= 1000) {
            String.format("%.1f T", totalVolume / 1000)
        } else {
            String.format("%.0f kg", totalVolume)
        }
    }
    
    // Get day of week
    fun getDayOfWeek(): String {
        val sdf = java.text.SimpleDateFormat("EEEE", java.util.Locale.getDefault())
        return sdf.format(java.util.Date(timestamp))
    }
    
    // Check if workout was done
    fun hasWorkout(): Boolean {
        return workoutCount > 0
    }
    
    // Get summary text
    fun getSummary(): String {
        return if (hasWorkout()) {
            "$workoutCount workout • $totalSets sets • ${getFormattedVolume()}"
        } else {
            "Rest Day"
        }
    }
}
