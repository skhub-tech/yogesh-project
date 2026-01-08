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
        ),
        
        // ADDITIONAL PROTEIN TIPS
        NutritionTip(
            id = "protein_4",
            title = "Vegetarian Protein Combo",
            description = "Mix 2 protein sources for better absorption. Dal + Rice, Rajma + Roti, Chickpeas + Wheat give complete protein. Non-veg: Egg + Whole grain roti.",
            category = "protein"
        ),
        NutritionTip(
            id = "protein_5",
            title = "Protein Distribution",
            description = "Spread protein throughout day. Each meal: 20-30g protein. Better than eating all at once. Breakfast, lunch, dinner all should have protein source.",
            category = "protein"
        ),
        
        // ADDITIONAL HYDRATION TIPS
        NutritionTip(
            id = "hydration_4",
            title = "Signs of Dehydration",
            description = "Yellow urine = drink more water. Clear/light yellow = good hydration. Also watch for: dry mouth, headaches, tiredness. Drink water consistently.",
            category = "hydration"
        ),
        NutritionTip(
            id = "hydration_5",
            title = "Post-Workout Hydration",
            description = "Drink 500-750ml water after workout. Add pinch of salt + lemon if intense session (electrolytes). Coconut water excellent choice post-workout.",
            category = "hydration"
        ),
        
        // ADDITIONAL TIMING TIPS
        NutritionTip(
            id = "timing_5",
            title = "Small Frequent Meals",
            description = "Eat every 3-4 hours. 5-6 small meals better than 2-3 large ones. Keeps metabolism active, prevents overeating, maintains energy levels.",
            category = "timing"
        ),
        
        // ADDITIONAL INDIAN DIET TIPS
        NutritionTip(
            id = "indian_5",
            title = "Spice Benefits",
            description = "Indian spices boost health! Turmeric (anti-inflammatory), Cumin (digestion), Ginger (immunity), Cinnamon (blood sugar). Use liberally in cooking.",
            category = "indian_diet"
        ),
        NutritionTip(
            id = "indian_6",
            title = "Dal Varieties",
            description = "Rotate dals for variety. Moong (light, easy digest), Chana (high protein), Masoor (iron-rich), Toor (balanced). Each has unique benefits.",
            category = "indian_diet"
        ),
        NutritionTip(
            id = "indian_7",
            title = "Healthy Snacking Indian Style",
            description = "Smart Indian snacks: Makhana, roasted chana, sprouts salad, cucumber raita, fruit with nuts. Avoid: samosa, pakora, chips daily.",
            category = "indian_diet"
        ),
        
        // ADDITIONAL WEIGHT LOSS TIPS
        NutritionTip(
            id = "weightloss_3",
            title = "Portion Control Tricks",
            description = "Use smaller plates. Fill half plate with sabzi/salad. Quarter with dal/protein. Quarter with rice/roti. Eat slowly, chew well (20-30 times).",
            category = "weight_loss"
        ),
        NutritionTip(
            id = "weightloss_4",
            title = "Avoid Liquid Calories",
            description = "Skip sugary drinks, packaged juices, soda. These add calories without filling you up. Stick to water, chaas, lemon water, green tea.",
            category = "weight_loss"
        ),
        
        // ADDITIONAL MUSCLE GAIN TIPS
        NutritionTip(
            id = "musclegain_3",
            title = "Pre-Bed Protein",
            description = "Eat slow-digesting protein before bed. Paneer (100g) or curd (1 bowl) or casein shake. Feeds muscles overnight during recovery. Good for muscle gain.",
            category = "muscle_gain"
        ),
        
        // ADDITIONAL SUPPLEMENT TIPS
        NutritionTip(
            id = "supplement_3",
            title = "Multivitamin - Do You Need?",
            description = "If balanced diet, may not need. Vegetarians: consider B12 supplement. Get blood test first. Don't rely on supplements, focus on real food first.",
            category = "supplements"
        ),
        
        // ADDITIONAL GENERAL TIPS
        NutritionTip(
            id = "general_3",
            title = "Sleep and Nutrition",
            description = "Poor sleep = increased hunger + cravings. Aim 7-8 hours sleep. Good sleep improves recovery, muscle growth, fat loss. Sleep is foundation of fitness.",
            category = "general"
        ),
        NutritionTip(
            id = "general_4",
            title = "Track Your Food",
            description = "Track meals for 1 week to know your actual intake. Eye-opening! Use apps or notes. Adjust based on goals. Aware eating = smarter choices.",
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
