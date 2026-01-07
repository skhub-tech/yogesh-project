package com.fitnessapp.ui.diet

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fitnessapp.model.User
import com.fitnessapp.repository.UserRepository
import kotlinx.coroutines.launch

class DietViewModel : ViewModel() {

    private val userRepository = UserRepository()

    private val _calories = MutableLiveData<Int>()
    val calories: LiveData<Int> get() = _calories

    private val _macros = MutableLiveData<String>()
    val macros: LiveData<String> get() = _macros
    
    private val _recommendations = MutableLiveData<String>()
    val recommendations: LiveData<String> get() = _recommendations

    init {
        calculateDiet()
    }

    private fun calculateDiet() {
        viewModelScope.launch {
            val uid = userRepository.getCurrentUserUid()
            if (uid != null) {
                val result = userRepository.getUser(uid)
                val user = result.getOrNull()
                if (user != null) {
                    val bmr = calculateBMR(user)
                    val tdee = (bmr * 1.55).toInt() // Moderately active
                    
                    val targetCalories = when (user.fitnessGoal) {
                        "fat_loss" -> tdee - 500
                        "muscle_gain" -> tdee + 300
                        else -> tdee
                    }
                    
                    _calories.value = targetCalories
                    
                    // Simple macro split: 30% P, 35% C, 35% F for balanced
                    // 40% P, 30% C, 30% F for muscle
                    // 40% P, 20% C, 40% F for fat loss
                    
                    val pRatio = if(user.fitnessGoal == "fat_loss") 0.4 else if(user.fitnessGoal == "muscle_gain") 0.35 else 0.3
                    val cRatio = if(user.fitnessGoal == "fat_loss") 0.2 else if(user.fitnessGoal == "muscle_gain") 0.45 else 0.4
                    val fRatio = 1.0 - pRatio - cRatio
                    
                    val pGrams = (targetCalories * pRatio / 4).toInt()
                    val cGrams = (targetCalories * cRatio / 4).toInt()
                    val fGrams = (targetCalories * fRatio / 9).toInt()
                    
                    _macros.value = "Protein: ${pGrams}g | Carbs: ${cGrams}g | Fat: ${fGrams}g"
                    
                    // Recommendations
                    val recs = StringBuilder()
                    if (user.fitnessGoal == "fat_loss") {
                        recs.append("• Eat high volume foods (veggies, salads)\n")
                        recs.append("• Avoid sugary drinks\n")
                        recs.append("• Prioritize protein in every meal\n")
                    } else if (user.fitnessGoal == "muscle_gain") {
                        recs.append("• Eat complex carbs pre-workout\n")
                        recs.append("• Protein shake post-workout\n")
                        recs.append("• Don't skip meals\n")
                    } else {
                        recs.append("• Balanced diet with fruits and vegetables\n")
                        recs.append("• Stay hydrated\n")
                    }
                    
                    // Add Indian food examples
                    recs.append("\nRecommended Indian Foods:\n")
                    recs.append("• High Protein: Paneer, Chicken Tikka, Dal, Chana Masala\n")
                    recs.append("• Healthy Carbs: Roti, Brown Rice, Sweet Potato\n")
                    recs.append("• Healthy Fats: Almonds, Ghee (in moderation)\n")
                    
                    _recommendations.value = recs.toString()
                }
            }
        }
    }

    private fun calculateBMR(user: User): Double {
        // Mifflin-St Jeor
        // Weight in kg, Height in cm, Age in years
        val s = if (user.gender.equals("male", ignoreCase = true)) 5 else -161
        return (10 * user.weightKg) + (6.25 * user.heightCm) - (5 * user.age) + s
    }
}
