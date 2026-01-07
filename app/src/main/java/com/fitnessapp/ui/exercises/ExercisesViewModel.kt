package com.fitnessapp.ui.exercises

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fitnessapp.model.Exercise
import com.fitnessapp.repository.FitnessRepository
import kotlinx.coroutines.launch

class ExercisesViewModel : ViewModel() {

    private val repository = FitnessRepository()
    
    private val _exercises = MutableLiveData<List<Exercise>>()
    val exercises: LiveData<List<Exercise>> get() = _exercises

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    init {
        loadExercises()
    }

    private fun loadExercises() {
        _isLoading.value = true
        viewModelScope.launch {
            val result = repository.getExercises()
            if (result.isSuccess) {
                // If empty (first run), add dummy data
                val list = result.getOrNull() ?: emptyList()
                if (list.isEmpty()) {
                     populateDummyData()
                } else {
                    _exercises.value = list
                }
            } else {
                // Handle error
            }
             _isLoading.value = false
        }
    }

    fun addExercise(exercise: Exercise) {
        viewModelScope.launch {
            _isLoading.value = true
            val result = repository.addExercise(exercise)
            if (result.isSuccess) {
                // Refresh list
                loadExercises()
            }
            _isLoading.value = false
        }
    }
    
    private fun populateDummyData() {
        val dummyList = listOf(
            // Chest
            Exercise("1", "Push-ups", "Chest", "Beginner", "A classic bodyweight exercise for chest devleopment."),
            Exercise("2", "Bench Press", "Chest", "Intermediate", "Compound movement for upper body strength."),
            Exercise("3", "Incline Dumbbell Press", "Chest", "Intermediate", "Targets the upper chest muscles."),
            
            // Back
            Exercise("4", "Pull-ups", "Back", "Intermediate", "Excellent for building back width and strength."),
            Exercise("5", "Dumbbell Rows", "Back", "Beginner", "Targets the latissimus dorsi muscles."),
            Exercise("6", "Deadlift", "Back", "Advanced", "Full body compound movement, heavy on posterior chain."),

            // Legs
            Exercise("7", "Squats", "Legs", "Beginner", "The king of leg exercises."),
            Exercise("8", "Lunges", "Legs", "Beginner", "Unilateral leg exercise for balance and strength."),
            Exercise("9", "Leg Press", "Legs", "Beginner", "Machine-based leg exercise."),

            // Shoulders
            Exercise("10", "Overhead Press", "Shoulders", "Intermediate", "Compound movement for shoulder mass."),
            Exercise("11", "Lateral Raises", "Shoulders", "Beginner", "Isolates the side deltoids."),

            // Arms
            Exercise("12", "Bicep Curls", "Biceps", "Beginner", "Isolation exercise for biceps."),
            Exercise("13", "Tricep Dips", "Triceps", "Intermediate", "Bodyweight exercise for triceps."),

            // Core
            Exercise("14", "Plank", "Core", "Beginner", "Isometric core stability exercise."),
            Exercise("15", "Crunches", "Core", "Beginner", "Targets abdominal muscles.")
        )
        _exercises.value = dummyList
    }
}
