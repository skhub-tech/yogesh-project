package com.fitnessapp.model

data class WeeklySchedule(
    val monday: DailyWorkout? = null,
    val tuesday: DailyWorkout? = null,
    val wednesday: DailyWorkout? = null,
    val thursday: DailyWorkout? = null,
    val friday: DailyWorkout? = null,
    val saturday: DailyWorkout? = null,
    val sunday: DailyWorkout? = null
)

data class DailyWorkout(
    val dayName: String = "",
    val focus: String = "", // e.g. "Chest & Triceps", "Cardio"
    val exercises: List<PlanExercise> = emptyList(),
    val isRestDay: Boolean = false
)

data class PlanExercise(
    val name: String = "",
    val sets: Int = 3,
    val reps: String = "10-12",
    val description: String = ""
)
