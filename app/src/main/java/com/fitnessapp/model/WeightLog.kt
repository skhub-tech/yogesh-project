package com.fitnessapp.model

data class WeightLog(
    val id: String = "",
    val userId: String = "",
    val weight: Double = 0.0,
    val timestamp: Long = System.currentTimeMillis(),
    val date: String = "", // Format: "2026-01-09"
    val bmi: Double = 0.0,
    val notes: String = ""
) {
    // Helper function to get formatted date
    fun getFormattedDate(): String {
        val sdf = java.text.SimpleDateFormat("MMM dd, yyyy", java.util.Locale.getDefault())
        return sdf.format(java.util.Date(timestamp))
    }
    
    // Helper function to get formatted weight
    fun getFormattedWeight(): String {
        return String.format("%.1f kg", weight)
    }
}
