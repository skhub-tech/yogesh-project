package com.fitnessapp.model

data class PersonalRecord(
    val exerciseName: String = "",
    val bestWeight: Double = 0.0,
    val bestReps: Int = 0,
    val estimated1RM: Double = 0.0,
    val achievedDate: Long = System.currentTimeMillis(),
    val userId: String = ""
) {
    // Calculate 1RM using Epley formula
    fun calculate1RM(): Double {
        if (bestReps == 1) return bestWeight
        return bestWeight * (1 + bestReps / 30.0)
    }
    
    // Get formatted date
    fun getFormattedDate(): String {
        val sdf = java.text.SimpleDateFormat("MMM dd, yyyy", java.util.Locale.getDefault())
        return sdf.format(java.util.Date(achievedDate))
    }
    
    // Check if this is a recent PR (within last 7 days)
    fun isRecent(): Boolean {
        val sevenDaysAgo = System.currentTimeMillis() - (7 * 24 * 60 * 60 * 1000)
        return achievedDate > sevenDaysAgo
    }
}
