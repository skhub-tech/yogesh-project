package com.fitnessapp.data

import com.fitnessapp.model.NutritionTip

object NutritionTipsData {
    
    private val tipsList = listOf(
        // PROTEIN TIPS
        NutritionTip(
            id = "protein_1",
            title = "Protein Intake for Indians",
            description = "Aim for 1.6-2.2g protein per kg body weight. Good Indian sources: Dal (24g/100g), Paneer (18g/100g), Chicken (25g/100g), Eggs (13g per egg), Rajma (22g/100g), Moong sprouts (24g/100g).",
            category = "protein"
        ),
        NutritionTip(
            id = "protein_2",
            title = "Complete Your Protein",
            description = "Rice + Dal = Complete protein! Combining grains and legumes gives all essential amino acids. Also: Roti + Dal, Khichdi, Idli + Sambar.",
            category = "protein"
        ),
        NutritionTip(
            id = "protein_3",
            title = "Post-Workout Protein",
            description = "Consume 20-40g protein within 2 hours of workout. Indian options: 3-4 eggs, Paneer bhurji (100g), Chicken curry (150g), or protein shake with banana.",
            category = "protein"
        ),
        
        // HYDRATION TIPS
        NutritionTip(
            id = "hydration_1",
            title = "Daily Water Intake",
            description = "Drink 2.5-3.5 liters water daily. Start with 2 glasses upon waking (500ml). Add lemon, jeera, or mint for flavor. Indian summer? Increase by 1L.",
            category = "hydration"
        ),
        NutritionTip(
            id = "hydration_2",
            title = "Pre-Workout Hydration",
            description = "Drink 400-500ml water 2 hours before workout. Avoid drinking too much right before (causes discomfort). Green coconut water is excellent pre-workout in summers.",
            category = "hydration"
        ),
        NutritionTip(
            id = "hydration_3",
            title = "Hydrating Indian Drinks",
            description = "Beyond water: Nimbu pani (electrolytes), Coconut water (potassium), Buttermilk/Chaas (probiotics + hydration), Cucumber water, Sattu drink.",
            category = "hydration"
        ),
        
        // MEAL TIMING TIPS
        NutritionTip(
            id = "timing_1",
            title = "Breakfast Like a King",
            description = "Never skip breakfast! Eat within 1 hour of waking. Indian options: Poha, Upma, Paratha with curd, Oats upma, Egg bhurji with roti. Include protein + complex carbs.",
            category = "timing"
        ),
        NutritionTip(
            id = "timing_2",
            title = "Pre-Workout Meal",
            description = "Eat 1-2 hours before workout. Light carbs + moderate protein. Good options: Banana + peanut butter, Oats, Upma, 2 rotis with dal (small portion).",
            category = "timing"
        ),
        NutritionTip(
            id = "timing_3",
            title = "Post-Workout Window",
            description = "Eat within 2 hours post-workout. Combo of protein + carbs. Options: Rice + Dal + Chicken, Curd with fruits, Paneer with roti, Banana + Eggs.",
            category = "timing"
        ),
        NutritionTip(
            id = "timing_4",
            title = "Dinner Before 8 PM",
            description = "Eat dinner 2-3 hours before sleep. Keep it lighter than lunch. Avoid heavy gravies. Good: Dal, sabzi, small roti, mild chicken/fish curry with salad.",
            category = "timing"
        ),
        
        // INDIAN DIET SPECIFIC
        NutritionTip(
            id = "indian_1",
            title = "Smart Roti Choices",
            description = "Whole wheat roti > Maida roti. Mix bajra, jowar, ragi for variety. 2-3 rotis per meal typical. Rotate grains for different micronutrients.",
            category = "indian_diet"
        ),
        NutritionTip(
            id = "indian_2",
            title = "Rice Portions",
            description = "White rice: 1 cup cooked (200 cal). Brown rice preferred (more fiber). Pair with protein (dal/chicken). Avoid excess rice at dinner for weight loss.",
            category = "indian_diet"
        ),
        NutritionTip(
            id = "indian_3",
            title = "Smart Oil Usage",
            description = "Use mustard oil, olive oil, or ghee (in moderation). Avoid reusing oil. 2-3 tablespoons oil per day total. Tadka uses minimal oil - perfect!",
            category = "indian_diet"
        ),
        NutritionTip(
            id = "indian_4",
            title = "Paneer vs Chicken",
            description = "Both great protein! Paneer: 18g protein, 20g fat per 100g. Chicken: 25g protein, 3g fat per 100g. Chicken better for fat loss, Paneer good for vegetarians.",
            category = "indian_diet"
        ),
        
        // WEIGHT LOSS SPECIFIC
        NutritionTip(
            id = "weightloss_1",
            title = "Calorie Deficit Basics",
            description = "For weight loss: Eat 300-500 cal below TDEE. Track for 1 week. Adjust if no progress. Never go below 1200-1500 cal (unsafe).",
            category = "weight_loss"
        ),
        NutritionTip(
            id = "weightloss_2",
            title = "Indian Weight Loss Foods",
            description = "High volume, low calorie: Salads, Vegetable sabzi, Moong sprouts, Cucumber raita, Dal (without tadka), Grilled chicken, Boiled eggs.",
            category = "weight_loss"
        ),
        
        // MUSCLE GAIN SPECIFIC
        NutritionTip(
            id = "musclegain_1",
            title = "Calorie Surplus for Gains",
            description = "Eat 200-300 cal above TDEE. Slow bulk = less fat gain. Prioritize protein. Indian muscle foods: High dal intake, paneer, eggs, chicken, fish, curd.",
            category = "muscle_gain"
        ),
        NutritionTip(
            id = "musclegain_2",
            title = "Carbs for Energy",
            description = "Need carbs for intense workouts! Rice, roti, oats, sweet potato, banana. Time carbs around workout. Carbs post-workout critical for recovery.",
            category = "muscle_gain"
        ),
        
        // SUPPLEMENTS (Optional)
        NutritionTip(
            id = "supplement_1",
            title = "Protein Powder - When?",
            description = "Only if can't meet protein from food. Whey protein (fast-absorbing). Take post-workout or between meals. 1 scoop = ~25g protein. Indian brands: MuscleBlaze, Optimum Nutrition.",
            category = "supplements"
        ),
        NutritionTip(
            id = "supplement_2",
            title = "Creatine - The Basics",
            description = "Proven supplement. 5g/day any time. Helps strength, muscle gain. Drink extra water. Safe long-term. Vegetarians benefit more (no meat intake).",
            category = "supplements"
        ),
        
        // GENERAL TIPS
        NutritionTip(
            id = "general_1",
            title = "Cheat Meals, Not Cheat Days",
            description = "1-2 cheat MEALS per week OK. Enjoy your favorite Indian food guilt-free. Don't let it become a binge. Get back on track next meal.",
            category = "general"
        ),
        NutritionTip(
            id = "general_2",
            title = "Consistency > Perfection",
            description = "80% clean eating + 20% flexibility. Sustainable diet > extreme restrictions. Indian food is healthy - just control portions and oil. Focus on whole foods.",
            category = "general"
        )
    )
    
    fun getTipsByCategory(category: String): List<NutritionTip> {
        return tipsList.filter { it.category == category }
    }
    
    fun getAllTips(): List<NutritionTip> {
        return tipsList
    }
}
