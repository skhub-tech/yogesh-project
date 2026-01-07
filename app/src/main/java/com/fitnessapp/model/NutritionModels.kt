package com.fitnessapp.model

data class Recipe(
    val id: String,
    val name: String,
    val description: String,
    val category: String, // breakfast, lunch, dinner, snack
    val dietType: String, // veg, non-veg
    val ingredients: List<String>,
    val instructions: List<String>,
    val calories: Int,
    val protein: Int, // grams
    val carbs: Int, // grams
    val fats: Int, // grams
    val prepTime: Int, // minutes
    val cookTime: Int, // minutes
    val servings: Int,
    val difficulty: String, // easy, medium, hard
    val tags: List<String> // indian, high-protein, keto, etc.
)

data class Macros(
    val protein: Int, // grams
    val carbs: Int, // grams
    val fats: Int, // grams
    val calories: Int
)

data class MealPlan(
    val id: String,
    val name: String,
    val goal: String, // weight_loss, muscle_gain, maintenance
    val description: String,
    val dailyCalories: Int,
    val macros: Macros,
    val meals: List<MealSchedule>
)

data class MealSchedule(
    val mealType: String, // breakfast, lunch, dinner, snack
    val time: String,
    val recipeIds: List<String>,
    val calories: Int,
    val macros: Macros
)

data class NutritionTip(
    val id: String,
    val title: String,
    val description: String,
    val category: String // protein, hydration, timing, supplements
)
