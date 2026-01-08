package com.fitnessapp.ui.exercises

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fitnessapp.R
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
            Exercise("1", "Push-ups", "Chest", "Beginner", "A classic bodyweight exercise for chest devleopment.", "", 0.0, R.drawable.pushup),
            Exercise("2", "Bench Press", "Chest", "Intermediate", "Compound movement for upper body strength.", "", 0.0, R.drawable.bench_press),
            Exercise("3", "Incline Dumbbell Press", "Chest", "Intermediate", "Targets the upper chest muscles.", "", 0.0, R.drawable.incline_dumbbell_press),
            
            // Back
            Exercise("4", "Pull-ups", "Back", "Intermediate", "Excellent for building back width and strength.", "", 0.0, R.drawable.pullup),
            Exercise("5", "Dumbbell Rows", "Back", "Beginner", "Targets the latissimus dorsi muscles.", "", 0.0, R.drawable.dumbbell_row),
            Exercise("6", "Deadlift", "Back", "Advanced", "Full body compound movement, heavy on posterior chain.", "", 0.0, R.drawable.deadlift),

            // Legs
            Exercise("7", "Squats", "Legs", "Beginner", "The king of leg exercises.", "", 0.0, R.drawable.squat),
            Exercise("8", "Lunges", "Legs", "Beginner", "Unilateral leg exercise for balance and strength.", "", 0.0, R.drawable.lunge),
            Exercise("9", "Leg Press", "Legs", "Beginner", "Machine-based leg exercise.", "", 0.0, R.drawable.leg_press),

            // Shoulders
            Exercise("10", "Overhead Press", "Shoulders", "Intermediate", "Compound movement for shoulder mass.", "", 0.0, R.drawable.overhead_press),
            Exercise("11", "Lateral Raises", "Shoulders", "Beginner", "Isolates the side deltoids.", "", 0.0, R.drawable.lateral_raise),

            // Arms
            Exercise("12", "Bicep Curls", "Biceps", "Beginner", "Isolation exercise for biceps.", "", 0.0, R.drawable.bicep_curl),
            Exercise("13", "Tricep Dips", "Triceps", "Intermediate", "Bodyweight exercise for triceps.", "", 0.0, R.drawable.tricep_dip),

            // Core
            Exercise("14", "Plank", "Core", "Beginner", "Isometric core stability exercise.", "", 0.0, R.drawable.plank),
            Exercise("15", "Crunches", "Core", "Beginner", "Targets abdominal muscles.", "", 0.0, R.drawable.crunch)
        )
        _exercises.value = dummyList
    }
}
