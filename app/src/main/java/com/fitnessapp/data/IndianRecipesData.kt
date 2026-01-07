package com.fitnessapp.data

import com.fitnessapp.model.Recipe

object IndianRecipesData {
    
    // BREAKFAST RECIPES
    private val breakfastRecipes = listOf(
        Recipe(
            id = "breakfast_poha",
            name = "Vegetable Poha",
            description = "Healthy flattened rice with vegetables and spices",
            category = "breakfast",
            dietType = "veg",
            ingredients = listOf(
                "1 cup poha (flattened rice)",
                "1 potato (diced)",
                "1/4 cup peanuts",
                "1 onion (chopped)",
                "2 green chillies",
                "Curry leaves",
                "1 tsp mustard seeds",
                "1/2 tsp turmeric",
                "Salt to taste"
            ),
            instructions = listOf(
                "Rinse poha in water and drain",
                "Heat oil, add mustard seeds and curry leaves",
                "Add peanuts and fry",
                "Add onions, green chillies, sauté",
                "Add potatoes, turmeric, salt",
                "Add poha, mix gently",
                "Cook for 3-4 minutes",
                "Garnish with coriander and lemon"
            ),
            calories = 320,
            protein = 8,
            carbs = 52,
            fats = 9,
            prepTime = 10,
            cookTime = 15,
            servings = 2,
            difficulty = "easy",
            tags = listOf("indian", "veg", "breakfast", "low-fat")
        ),
        Recipe(
            id = "breakfast_upma",
            name = "Vegetable Upma",
            description = "Savory semolina porridge with vegetables",
            category = "breakfast",
            dietType = "veg",
            ingredients = listOf(
                "1 cup rava (semolina)",
                "Mixed vegetables (carrot, peas, beans)",
                "1 onion",
                "2 green chillies",
                "Curry leaves",
                "1 tsp mustard seeds",
                "2 cups water",
                "Salt to taste"
            ),
            instructions = listOf(
                "Dry roast rava until fragrant",
                "Heat oil, add mustard seeds",
                "Add curry leaves, onions, chillies",
                "Add vegetables, sauté",
                "Add water, bring to boil",
                "Slowly add roasted rava while stirring",
                "Cook until water absorbs",
                "Serve hot with coconut chutney"
            ),
            calories = 290,
            protein = 7,
            carbs = 48,
            fats = 7,
            prepTime = 10,
            cookTime = 15,
            servings = 2,
            difficulty = "easy",
            tags = listOf("indian", "veg", "breakfast")
        ),
        Recipe(
            id = "breakfast_egg_bhurji",
            name = "Egg Bhurji",
            description = "Indian style scrambled eggs with spices",
            category = "breakfast",
            dietType = "non-veg",
            ingredients = listOf(
                "4 eggs",
                "1 onion (chopped)",
                "1 tomato (chopped)",
                "2 green chillies",
                "1/2 tsp turmeric",
                "1/2 tsp red chilli powder",
                "Coriander leaves",
                "Salt to taste"
            ),
            instructions = listOf(
                "Beat eggs in a bowl",
                "Heat oil, sauté onions",
                "Add tomatoes, cook until soft",
                "Add spices (turmeric, chilli powder)",
                "Pour beaten eggs",
                "Scramble while cooking",
                "Cook for 5 minutes",
                "Garnish with coriander"
            ),
            calories = 380,
            protein = 28,
            carbs = 12,
            fats = 24,
            prepTime = 5,
            cookTime = 10,
            servings = 2,
            difficulty = "easy",
            tags = listOf("indian", "non-veg", "breakfast", "high-protein")
        ),
        Recipe(
            id = "breakfast_oats_upma",
            name = "Oats Upma",
            description = "Healthy oats cooked Indian style",
            category = "breakfast",
            dietType = "veg",
            ingredients = listOf(
                "1 cup oats",
                "Mixed vegetables",
                "1 onion",
                "2 green chillies",
                "Curry leaves",
                "1 tsp mustard seeds",
                "1.5 cups water",
                "Salt to taste"
            ),
            instructions = listOf(
                "Dry roast oats for 2-3 minutes",
                "Heat oil, add mustard seeds",
                "Add curry leaves, onions, chillies",
                "Add vegetables",
                "Add water and salt",
                "Add roasted oats",
                "Cook for 5 minutes",
                "Serve hot"
            ),
            calories = 240,
            protein = 9,
            carbs = 38,
            fats = 6,
            prepTime = 5,
            cookTime = 10,
            servings = 2,
            difficulty = "easy",
            tags = listOf("indian", "veg", "breakfast", "high-fiber")
        ),
        Recipe(
            id = "breakfast_paratha",
            name = "Aloo Paratha",
            description = "Whole wheat flatbread stuffed with spiced potatoes",
            category = "breakfast",
            dietType = "veg",
            ingredients = listOf(
                "2 cups whole wheat flour",
                "2 potatoes (boiled, mashed)",
                "1 onion (chopped)",
                "2 green chillies",
                "1 tsp garam masala",
                "Coriander leaves",
                "Salt to taste",
                "Ghee for cooking"
            ),
            instructions = listOf(
                "Knead dough with flour and water",
                "Mix mashed potatoes with spices",
                "Roll dough into small circles",
                "Place potato filling in center",
                "Seal and roll again",
                "Cook on hot tawa with ghee",
                "Flip until golden brown",
                "Serve with curd or pickle"
            ),
            calories = 420,
            protein = 10,
            carbs = 68,
            fats = 12,
            prepTime = 20,
            cookTime = 15,
            servings = 2,
            difficulty = "medium",
            tags = listOf("indian", "veg", "breakfast")
        )
    )
    
    // LUNCH/DINNER RECIPES
    private val lunchDinnerRecipes = listOf(
        Recipe(
            id = "lunch_dal_tadka",
            name = "Dal Tadka",
            description = "Protein-rich lentil curry with tempering",
            category = "lunch",
            dietType = "veg",
            ingredients = listOf(
                "1 cup toor dal (yellow lentils)",
                "1 onion",
                "2 tomatoes",
                "2 green chillies",
                "Ginger-garlic paste",
                "1 tsp cumin seeds",
                "1/2 tsp turmeric",
                "Red chilli powder",
                "Ghee for tempering"
            ),
            instructions = listOf(
                "Pressure cook dal with turmeric",
                "Mash dal slightly",
                "Heat ghee, add cumin seeds",
                "Add onions, ginger-garlic",
                "Add tomatoes, cook until soft",
                "Add spices",
                "Add cooked dal, mix well",
                "Simmer for 10 minutes"
            ),
            calories = 280,
            protein = 16,
            carbs = 42,
            fats = 6,
            prepTime = 10,
            cookTime = 20,
            servings = 4,
            difficulty = "easy",
            tags = listOf("indian", "veg", "lunch", "high-protein", "dal")
        ),
        Recipe(
            id = "lunch_chicken_curry",
            name = "Home-Style Chicken Curry",
            description = "Flavorful chicken cooked in Indian spices",
            category = "lunch",
            dietType = "non-veg",
            ingredients = listOf(
                "500g chicken (cut into pieces)",
                "2 onions",
                "2 tomatoes",
                "Ginger-garlic paste",
                "1 tsp coriander powder",
                "1 tsp cumin powder",
                "1/2 tsp turmeric",
                "1 tsp garam masala",
                "Coriander leaves"
            ),
            instructions = listOf(
                "Marinate chicken with turmeric and salt",
                "Heat oil, sauté onions until golden",
                "Add ginger-garlic paste",
                "Add tomatoes, cook until soft",
                "Add all spices",
                "Add chicken, cook for 5 minutes",
                "Add water, cover and cook 20 minutes",
                "Garnish with coriander"
            ),
            calories = 340,
            protein = 38,
            carbs = 12,
            fats = 16,
            prepTime = 15,
            cookTime = 30,
            servings = 4,
            difficulty = "medium",
            tags = listOf("indian", "non-veg", "lunch", "high-protein", "chicken")
        ),
        Recipe(
            id = "lunch_paneer_bhurji",
            name = "Paneer Bhurji",
            description = "Scrambled cottage cheese with vegetables",
            category = "lunch",
            dietType = "veg",
            ingredients = listOf(
                "200g paneer (crumbled)",
                "1 onion",
                "1 tomato",
                "1 capsicum",
                "2 green chillies",
                "1/2 tsp turmeric",
                "1 tsp red chilli powder",
                "Coriander leaves"
            ),
            instructions = listOf(
                "Heat oil, sauté onions",
                "Add capsicum, cook for 2 minutes",
                "Add tomatoes, spices",
                "Add crumbled paneer",
                "Mix well and cook for 5 minutes",
                "Garnish with coriander",
                "Serve with roti or rice"
            ),
            calories = 320,
            protein = 22,
            carbs = 14,
            fats = 20,
            prepTime = 10,
            cookTime = 15,
            servings = 3,
            difficulty = "easy",
            tags = listOf("indian", "veg", "lunch", "high-protein", "paneer")
        ),
        Recipe(
            id = "lunch_rajma",
            name = "Rajma Masala",
            description = "Kidney beans in thick tomato gravy",
            category = "lunch",
            dietType = "veg",
            ingredients = listOf(
                "1 cup rajma (kidney beans, soaked)",
                "2 onions",
                "2 tomatoes",
                "Ginger-garlic paste",
                "1 tsp cumin seeds",
                "Spices (turmeric, red chilli, coriander powder)",
                "Garam masala",
                "Coriander leaves"
            ),
            instructions = listOf(
                "Pressure cook soaked rajma until soft",
                "Heat oil, add cumin seeds",
                "Sauté onions until golden",
                "Add ginger-garlic, tomatoes",
                "Add all spices, cook until oil separates",
                "Add cooked rajma with water",
                "Simmer for 15 minutes",
                "Garnish and serve with rice"
            ),
            calories = 310,
            protein = 18,
            carbs = 48,
            fats = 6,
            prepTime = 10,
            cookTime = 40,
            servings = 4,
            difficulty = "medium",
            tags = listOf("indian", "veg", "lunch", "high-protein", "rajma")
        ),
        Recipe(
            id = "lunch_fish_curry",
            name = "Bengali Fish Curry",
            description = "Fish cooked in light mustard gravy",
            category = "lunch",
            dietType = "non-veg",
            ingredients = listOf(
                "4 fish pieces (rohu/katla)",
                "1 tsp mustard seeds",
                "1/2 tsp turmeric",
                "2 green chillies",
                "1 tomato",
                "Mustard oil",
                "Salt to taste"
            ),
            instructions = listOf(
                "Marinate fish with turmeric and salt",
                "Grind mustard seeds to paste",
                "Heat mustard oil, fry fish lightly",
                "Remove fish, add mustard paste",
                "Add turmeric, green chillies",
                "Add water and tomato",
                "Add fried fish, simmer 10 minutes",
                "Serve with rice"
            ),
            calories = 280,
            protein = 32,
            carbs = 8,
            fats = 14,
            prepTime = 15,
            cookTime = 20,
            servings = 4,
            difficulty = "medium",
            tags = listOf("indian", "non-veg", "lunch", "high-protein", "fish")
        )
    )
    
    // SNACK RECIPES
    private val snackRecipes = listOf(
        Recipe(
            id = "snack_boiled_chana",
            name = "Boiled Chana Chat",
            description = "Protein-rich chickpea snack",
            category = "snack",
            dietType = "veg",
            ingredients = listOf(
                "1 cup boiled chickpeas",
                "1 onion (chopped)",
                "1 tomato (chopped)",
                "1 cucumber (chopped)",
                "Lemon juice",
                "Chaat masala",
                "Coriander leaves"
            ),
            instructions = listOf(
                "Mix boiled chana with vegetables",
                "Add lemon juice",
                "Sprinkle chaat masala",
                "Garnish with coriander",
                "Serve immediately"
            ),
            calories = 180,
            protein = 12,
            carbs = 28,
            fats = 3,
            prepTime = 5,
            cookTime = 0,
            servings = 2,
            difficulty = "easy",
            tags = listOf("indian", "veg", "snack", "high-protein", "low-fat")
        ),
        Recipe(
            id = "snack_sprouts",
            name = "Moong Sprouts Salad",
            description = "Healthy moong bean sprouts",
            category = "snack",
            dietType = "veg",
            ingredients = listOf(
                "1 cup moong sprouts",
                "1 onion (chopped)",
                "1 tomato (chopped)",
                "Lemon juice",
                "Black salt",
                "Coriander"
            ),
            instructions = listOf(
                "Mix sprouts with vegetables",
                "Add lemon juice and black salt",
                "Toss well",
                "Garnish with coriander",
                "Eat fresh"
            ),
            calories = 150,
            protein = 14,
            carbs = 24,
            fats = 2,
            prepTime = 5,
            cookTime = 0,
            servings = 2,
            difficulty = "easy",
            tags = listOf("indian", "veg", "snack", "high-protein", "raw")
        ),
        Recipe(
            id = "snack_boiled_eggs",
            name = "Spiced Boiled Eggs",
            description = "Simple high-protein snack",
            category = "snack",
            dietType = "non-veg",
            ingredients = listOf(
                "4 eggs (boiled)",
                "Black salt",
                "Black pepper",
                "Red chilli powder"
            ),
            instructions = listOf(
                "Peel boiled eggs",
                "Cut in half",
                "Sprinkle spices",
                "Serve immediately"
            ),
            calories = 280,
            protein = 24,
            carbs = 2,
            fats = 20,
            prepTime = 2,
            cookTime = 0,
            servings = 2,
            difficulty = "easy",
            tags = listOf("indian", "non-veg", "snack", "high-protein", "keto")
        )
    )
    
    fun getAllRecipes(): List<Recipe> {
        return breakfastRecipes + lunchDinnerRecipes + snackRecipes
    }
    
    fun getRecipesByCategory(category: String): List<Recipe> {
        return getAllRecipes().filter { it.category == category }
    }
    
    fun getRecipesByDietType(dietType: String): List<Recipe> {
        return getAllRecipes().filter { it.dietType == dietType }
    }
    
    fun getRecipeById(id: String): Recipe? {
        return getAllRecipes().find { it.id == id }
    }
}
