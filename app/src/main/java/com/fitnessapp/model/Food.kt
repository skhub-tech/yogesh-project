package com.fitnessapp.model

data class Food(
    val id: String = "",
    val name: String = "",
    val calories: Int = 0,
    val protein: Double = 0.0,
    val carbs: Double = 0.0,
    val fat: Double = 0.0,
    val servingSize: String = "100g",
    val type: String = "veg" // veg, non-veg
)
