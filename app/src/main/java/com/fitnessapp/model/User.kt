package com.fitnessapp.model

data class User(
    val uid: String = "",
    val name: String = "",
    val email: String = "",
    val age: Int = 0,
    val gender: String = "",
    val heightCm: Double = 0.0,
    val weightKg: Double = 0.0,
    val fitnessGoal: String = "general_fitness" // fat_loss, muscle_gain, general_fitness
)
