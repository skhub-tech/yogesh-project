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
        ),
        Recipe(
            id = "breakfast_besan_chilla",
            name = "Besan Chilla",
            description = "Savory chickpea flour pancake",
            category = "breakfast",
            dietType = "veg",
            ingredients = listOf(
                "1 cup besan (chickpea flour)",
                "1 onion (finely chopped)",
                "1 tomato (chopped)",
                "2 green chillies",
                "Coriander leaves",
                "1/2 tsp turmeric",
                "1/2 tsp cumin powder",
                "Salt to taste",
                "Water as needed"
            ),
            instructions = listOf(
                "Mix besan with water to make batter",
                "Add all vegetables and spices",
                "Mix well, add more water if needed",
                "Heat tawa, spread batter like dosa",
                "Cook on both sides until golden",
                "Serve with chutney"
            ),
            calories = 220,
            protein = 12,
            carbs = 32,
            fats = 5,
            prepTime = 10,
            cookTime = 10,
            servings = 2,
            difficulty = "easy",
            tags = listOf("indian", "veg", "breakfast", "high-protein")
        ),
        Recipe(
            id = "breakfast_idli",
            name = "Idli Sambhar",
            description = "Steamed rice cakes with lentil curry",
            category = "breakfast",
            dietType = "veg",
            ingredients = listOf(
                "8-10 idlis (store bought or homemade)",
                "1 cup toor dal",
                "Mixed vegetables (carrot, beans, drumstick)",
                "Sambhar masala",
                "Tamarind pulp",
                "Curry leaves",
                "Mustard seeds"
            ),
            instructions = listOf(
                "Cook dal until soft",
                "Boil vegetables separately",
                "Add tamarind, sambhar masala to dal",
                "Add cooked vegetables",
                "Prepare tempering with mustard, curry leaves",
                "Add to sambhar",
                "Serve hot idlis with sambhar"
            ),
            calories = 260,
            protein = 10,
            carbs = 48,
            fats = 3,
            prepTime = 15,
            cookTime = 25,
            servings = 2,
            difficulty = "medium",
            tags = listOf("indian", "veg", "breakfast", "south-indian")
        ),
        Recipe(
            id = "breakfast_dosa",
            name = "Masala Dosa",
            description = "Crispy rice crepe with spiced potato filling",
            category = "breakfast",
            dietType = "veg",
            ingredients = listOf(
                "Dosa batter (1 cup)",
                "2 potatoes (boiled, mashed)",
                "1 onion",
                "Curry leaves",
                "Mustard seeds",
                "Turmeric",
                "Green chillies",
                "Oil for cooking"
            ),
            instructions = listOf(
                "Prepare potato masala filling",
                "Heat tawa, spread dosa batter thinly",
                "Cook until crispy",
                "Place potato masala on dosa",
                "Fold and serve with chutney, sambhar"
            ),
            calories = 340,
            protein = 8,
            carbs = 58,
            fats = 8,
            prepTime = 20,
            cookTime = 15,
            servings = 2,
            difficulty = "medium",
            tags = listOf("indian", "veg", "breakfast", "south-indian")
        ),
        Recipe(
            id = "breakfast_moong_chilla",
            name = "Moong Dal Chilla",
            description = "High-protein lentil pancake",
            category = "breakfast",
            dietType = "veg",
            ingredients = listOf(
                "1 cup moong dal (soaked 4-6 hours)",
                "1 onion (chopped)",
                "2 green chillies",
                "Ginger (1 inch)",
                "Coriander leaves",
                "Salt to taste",
                "Oil for cooking"
            ),
            instructions = listOf(
                "Grind soaked moong dal to smooth paste",
                "Add onions, chillies, ginger, coriander",
                "Mix well, add salt",
                "Heat tawa, spread batter",
                "Cook on both sides until golden",
                "Serve hot with chutney"
            ),
            calories = 250,
            protein = 16,
            carbs = 35,
            fats = 5,
            prepTime = 10,
            cookTime = 10,
            servings = 2,
            difficulty = "easy",
            tags = listOf("indian", "veg", "breakfast", "high-protein")
        ),
        Recipe(
            id = "breakfast_masala_omelette",
            name = "Masala Omelette",
            description = "Spiced Indian-style omelette",
            category = "breakfast",
            dietType = "non-veg",
            ingredients = listOf(
                "3 eggs",
                "1 onion (finely chopped)",
                "1 tomato (chopped)",
                "2 green chillies",
                "Coriander leaves",
                "1/4 tsp turmeric",
                "Black pepper",
                "Salt to taste"
            ),
            instructions = listOf(
                "Beat eggs in bowl",
                "Add all vegetables and spices",
                "Mix well",
                "Heat oil in pan",
                "Pour egg mixture",
                "Cook on both sides until done",
                "Serve hot with bread or roti"
            ),
            calories = 280,
            protein = 21,
            carbs = 8,
            fats = 18,
            prepTime = 5,
            cookTime = 7,
            servings = 2,
            difficulty = "easy",
            tags = listOf("indian", "non-veg", "breakfast", "high-protein", "keto")
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
        ),
        Recipe(
            id = "lunch_palak_paneer",
            name = "Palak Paneer",
            description = "Cottage cheese in creamy spinach gravy",
            category = "lunch",
            dietType = "veg",
            ingredients = listOf(
                "200g paneer (cubed)",
                "2 cups spinach (palak)",
                "1 onion",
                "2 tomatoes",
                "Ginger-garlic paste",
                "1/2 cup cream",
                "Spices (cumin, garam masala, turmeric)",
                "Salt to taste"
            ),
            instructions = listOf(
                "Blanch spinach, blend to smooth paste",
                "Sauté onions, ginger-garlic",
                "Add tomatoes, cook until soft",
                "Add spinach puree and spices",
                "Add cream, simmer",
                "Add paneer cubes gently",
                "Cook for 5 minutes",
                "Serve with roti or rice"
            ),
            calories = 320,
            protein = 18,
            carbs = 16,
            fats = 22,
            prepTime = 15,
            cookTime = 20,
            servings = 3,
            difficulty = "medium",
            tags = listOf("indian", "veg", "lunch", "paneer", "high-protein")
        ),
        Recipe(
            id = "lunch_chole",
            name = "Chole Masala",
            description = "Spicy chickpea curry",
            category = "lunch",
            dietType = "veg",
            ingredients = listOf(
                "1.5 cups chickpeas (soaked overnight)",
                "2 onions",
                "2 tomatoes",
                "Ginger-garlic paste",
                "2 tsp chole masala",
                "1 tsp cumin seeds",
                "Tea bag (for color)",
                "Coriander leaves"
            ),
            instructions = listOf(
                "Pressure cook chickpeas with tea bag",
                "Heat oil, add cumin",
                "Sauté onions until golden",
                "Add ginger-garlic, tomatoes",
                "Add chole masala and spices",
                "Add cooked chickpeas with water",
                "Simmer for 15 minutes",
                "Garnish with coriander"
            ),
            calories = 290,
            protein = 15,
            carbs = 44,
            fats = 6,
            prepTime = 10,
            cookTime = 35,
            servings = 4,
            difficulty = "medium",
            tags = listOf("indian", "veg", "lunch", "high-protein")
        ),
        Recipe(
            id = "lunch_aloo_gobi",
            name = "Aloo Gobi",
            description = "Potato and cauliflower dry curry",
            category = "lunch",
            dietType = "veg",
            ingredients = listOf(
                "2 potatoes (diced)",
                "1 cauliflower (small florets)",
                "1 onion",
                "2 tomatoes",
                "Ginger",
                "1 tsp cumin seeds",
                "Turmeric, coriander powder",
                "Coriander leaves"
            ),
            instructions = listOf(
                "Heat oil, add cumin seeds",
                "Add onions, ginger, sauté",
                "Add tomatoes and spices",
                "Add potatoes and cauliflower",
                "Cover and cook on low heat",
                "Stir occasionally until vegetables tender",
                "Garnish with coriander",
                "Serve with roti"
            ),
            calories = 240,
            protein = 6,
            carbs = 42,
            fats = 6,
            prepTime = 15,
            cookTime = 25,
            servings = 3,
            difficulty = "easy",
            tags = listOf("indian", "veg", "lunch", "dry-sabzi")
        ),
        Recipe(
            id = "lunch_keema",
            name = "Mutton Keema",
            description = "Spiced minced meat curry",
            category = "lunch",
            dietType = "non-veg",
            ingredients = listOf(
                "500g mutton keema (minced)",
                "2 onions",
                "2 tomatoes",
                "Ginger-garlic paste",
                "1 cup peas (optional)",
                "Spices (cumin, coriander, garam masala)",
                "Coriander leaves"
            ),
            instructions = listOf(
                "Heat oil, sauté onions",
                "Add ginger-garlic paste",
                "Add keema, cook until brown",
                "Add tomatoes and all spices",
                "Cook until oil separates",
                "Add peas and water",
                "Simmer for 20 minutes",
                "Garnish and serve"
            ),
            calories = 360,
            protein = 32,
            carbs = 14,
            fats = 20,
            prepTime = 10,
            cookTime = 30,
            servings = 4,
            difficulty = "medium",
            tags = listOf("indian", "non-veg", "lunch", "high-protein", "mutton")
        ),
        Recipe(
            id = "lunch_chicken_tikka",
            name = "Grilled Chicken Tikka",
            description = "Marinated grilled chicken pieces",
            category = "lunch",
            dietType = "non-veg",
            ingredients = listOf(
                "500g chicken (boneless cubes)",
                "1 cup yogurt",
                "Ginger-garlic paste",
                "1 tbsp tikka masala",
                "1 tsp red chilli powder",
                "Lemon juice",
                "Oil",
                "Salt to taste"
            ),
            instructions = listOf(
                "Mix yogurt with all spices",
                "Marinate chicken for 2-4 hours",
                "Skewer chicken pieces",
                "Grill or bake at 200°C for 15-20 min",
                "Brush with oil while grilling",
                "Serve hot with mint chutney"
            ),
            calories = 260,
            protein = 35,
            carbs = 6,
            fats = 10,
            prepTime = 130,
            cookTime = 20,
            servings = 4,
            difficulty = "medium",
            tags = listOf("indian", "non-veg", "lunch", "high-protein", "grilled")
        ),
        Recipe(
            id = "lunch_mixed_veg",
            name = "Mixed Vegetable Curry",
            description = "Healthy seasonal vegetables in curry",
            category = "lunch",
            dietType = "veg",
            ingredients = listOf(
                "Mixed vegetables (carrot, beans, peas, cauliflower)",
                "1 onion",
                "2 tomatoes",
                "Ginger-garlic paste",
                "Spices (cumin, coriander, garam masala)",
                "Kasuri methi",
                "Coriander leaves"
            ),
            instructions = listOf(
                "Chop all vegetables",
                "Sauté onions and ginger-garlic",
                "Add tomatoes and spices",
                "Add vegetables",
                "Add water, cover and cook",
                "Cook until vegetables tender",
                "Add kasuri methi",
                "Serve hot"
            ),
            calories = 180,
            protein = 6,
            carbs = 32,
            fats = 4,
            prepTime = 15,
            cookTime = 20,
            servings = 3,
            difficulty = "easy",
            tags = listOf("indian", "veg", "lunch", "low-fat", "healthy")
        ),
        Recipe(
            id = "lunch_egg_curry",
            name = "Egg Curry",
            description = "Boiled eggs in spicy tomato gravy",
            category = "lunch",
            dietType = "non-veg",
            ingredients = listOf(
                "6 eggs (boiled)",
                "2 onions",
                "2 tomatoes",
                "Ginger-garlic paste",
                "Spices (turmeric, red chilli, coriander powder)",
                "Garam masala",
                "Coriander leaves"
            ),
            instructions = listOf(
                "Boil and peel eggs",
                "Sauté onions until golden",
                "Add ginger-garlic, tomatoes",
                "Add all spices, cook until oil separates",
                "Add water to make gravy",
                "Add eggs gently",
                "Simmer for 10 minutes",
                "Garnish with coriander"
            ),
            calories = 320,
            protein = 26,
            carbs = 14,
            fats = 20,
            prepTime = 10,
            cookTime = 20,
            servings = 3,
            difficulty = "easy",
            tags = listOf("indian", "non-veg", "lunch", "high-protein", "egg")
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
        ),
        Recipe(
            id = "snack_makhana",
            name = "Roasted Makhana",
            description = "Healthy fox nuts roasted with spices",
            category = "snack",
            dietType = "veg",
            ingredients = listOf(
                "2 cups makhana (fox nuts)",
                "1 tsp ghee",
                "Black pepper",
                "Rock salt",
                "Chat masala"
            ),
            instructions = listOf(
                "Heat ghee in pan",
                "Add makhana, roast on low heat",
                "Keep stirring until crispy",
                "Sprinkle salt, pepper, chat masala",
                "Cool and store in airtight container"
            ),
            calories = 160,
            protein = 6,
            carbs = 28,
            fats = 4,
            prepTime = 2,
            cookTime = 8,
            servings = 2,
            difficulty = "easy",
            tags = listOf("indian", "veg", "snack", "low-fat", "healthy")
        ),
        Recipe(
            id = "snack_protein_smoothie",
            name = "Indian Protein Smoothie",
            description = "Banana almond protein shake",
            category = "snack",
            dietType = "veg",
            ingredients = listOf(
                "2 bananas",
                "1 cup milk",
                "10 almonds (soaked)",
                "2 dates",
                "1 scoop protein powder (optional)",
                "Cardamom powder"
            ),
            instructions = listOf(
                "Blend all ingredients until smooth",
                "Add ice if desired",
                "Serve immediately"
            ),
            calories = 320,
            protein = 15,
            carbs = 48,
            fats = 8,
            prepTime = 5,
            cookTime = 0,
            servings = 2,
            difficulty = "easy",
            tags = listOf("indian", "veg", "snack", "protein-shake", "post-workout")
        ),
        Recipe(
            id = "snack_peanut_chaat",
            name = "Roasted Peanut Chaat",
            description = "Protein-rich peanut salad",
            category = "snack",
            dietType = "veg",
            ingredients = listOf(
                "1 cup roasted peanuts",
                "1 onion (chopped)",
                "1 tomato (chopped)",
                "Lemon juice",
                "Chat masala",
                "Coriander leaves"
            ),
            instructions = listOf(
                "Mix roasted peanuts with vegetables",
                "Add lemon juice and chat masala",
                "Toss well",
                "Garnish with coriander",
                "Serve immediately"
            ),
            calories = 260,
            protein = 12,
            carbs = 18,
            fats = 16,
            prepTime = 5,
            cookTime = 0,
            servings = 2,
            difficulty = "easy",
            tags = listOf("indian", "veg", "snack", "high-protein")
        ),
        Recipe(
            id = "snack_paneer_tikka",
            name = "Paneer Tikka",
            description = "Grilled cottage cheese cubes",
            category = "snack",
            dietType = "veg",
            ingredients = listOf(
                "200g paneer (cubes)",
                "1 capsicum",
                "1 onion",
                "1/2 cup yogurt",
                "Ginger-garlic paste",
                "Tikka masala",
                "Lemon juice"
            ),
            instructions = listOf(
                "Marinate paneer with yogurt and spices",
                "Let it rest for 30 minutes",
                "Skewer paneer with vegetables",
                "Grill or bake until golden",
                "Serve hot with mint chutney"
            ),
            calories = 240,
            protein = 16,
            carbs = 10,
            fats = 15,
            prepTime = 35,
            cookTime = 15,
            servings = 2,
            difficulty = "medium",
            tags = listOf("indian", "veg", "snack", "high-protein", "paneer")
        ),
        Recipe(
            id = "snack_grilled_chicken",
            name = "Grilled Chicken Breast",
            description = "Simple grilled chicken with Indian spices",
            category = "snack",
            dietType = "non-veg",
            ingredients = listOf(
                "2 chicken breasts",
                "Lemon juice",
                "Black pepper",
                "Turmeric",
                "Garam masala",
                "Salt"
            ),
            instructions = listOf(
                "Marinate chicken with all spices",
                "Let it rest for 30 minutes",
                "Grill on medium heat",
                "Cook 6-8 minutes each side",
                "Slice and serve"
            ),
            calories = 220,
            protein = 38,
            carbs = 2,
            fats = 6,
            prepTime = 35,
            cookTime = 16,
            servings = 2,
            difficulty = "easy",
            tags = listOf("indian", "non-veg", "snack", "high-protein", "keto", "grilled")
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
