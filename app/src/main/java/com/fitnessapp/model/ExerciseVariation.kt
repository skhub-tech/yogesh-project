package com.fitnessapp.model

data class ExerciseVariation(
    val id: String = "",
    val name: String = "",
    val description: String = "",
    val difficulty: String = "Beginner", // Beginner, Intermediate, Advanced
    val instructions: List<String> = emptyList(),
    val targetMuscles: String = "",
    val equipment: String = "None", // None, Dumbbells, Barbell, etc.
    val stepImages: List<Int> = emptyList(), // Drawable resource IDs for step-by-step images
    val animationGif: Int = 0 // Drawable resource ID for GIF animation (0 = no GIF)
)
