package com.fitnessapp.model

data class DailyLog(
    val date: String = "", // YYYY-MM-DD
    val weight: Double = 0.0,
    val steps: Int = 0,
    val caloriesConsumed: Int = 0,
    val caloriesBurned: Int = 0,
    val waterIntakeMl: Int = 0,
    val completedWorkouts: List<String> = emptyList() // List of exercise IDs
)
