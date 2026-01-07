package com.fitnessapp.model

data class WorkoutSession(
    val date: String = "", // YYYY-MM-DD
    val exercises: List<ExerciseWithSets> = emptyList()
)

data class ExerciseWithSets(
    val exerciseName: String = "",
    val muscleGroup: String = "",
    val sets: List<ExerciseSetLog> = emptyList()
)
