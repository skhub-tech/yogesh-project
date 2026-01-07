package com.fitnessapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.fitnessapp.model.DailyWorkout
import com.fitnessapp.model.User
import com.fitnessapp.model.WeeklySchedule
import com.fitnessapp.services.FitnessPlanGenerator
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout
import java.util.Calendar
import java.util.Locale

class HomeViewModel : ViewModel() {

    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()

    private val _user = MutableLiveData<User?>()
    val user: LiveData<User?> get() = _user

    private val _todaysWorkout = MutableLiveData<DailyWorkout?>()
    val todaysWorkout: LiveData<DailyWorkout?> get() = _todaysWorkout

    private val _weeklyPlan = MutableLiveData<WeeklySchedule?>()
    
    private val _steps = MutableLiveData<Int>(0)
    val steps: LiveData<Int> get() = _steps

    private val _waterIntake = MutableLiveData<Int>(0)
    val waterIntake: LiveData<Int> get() = _waterIntake
    
    private val _bmi = MutableLiveData<Double>(0.0)
    val bmi: LiveData<Double> get() = _bmi

    init {
        fetchUserData()
    }

    private fun fetchUserData() {
        auth.currentUser?.let { firebaseUser ->
            // Use coroutine for background work
            viewModelScope.launch {
                try {
                    val document = withContext(kotlinx.coroutines.Dispatchers.IO) {
                        try {
                            // Add timeout
                            kotlinx.coroutines.withTimeout(5000L) {
                                db.collection("users").document(firebaseUser.uid).get().await()
                            }
                        } catch (e: Exception) {
                            null // Handle timeout or network error
                        }
                    }

                    if (document != null && document.exists()) {
                        val user = document.toObject(User::class.java)
                        _user.value = user
                        user?.let { 
                            generateAndSetPlan(it) 
                        }
                    } else {
                        // Handle offline or missing data - maybe load from local cache/prefs in future
                        // For now just don't crash
                        android.util.Log.e("HomeViewModel", "Failed to fetch user or doc doesn't exist")
                    }
                } catch (e: Exception) {
                    android.util.Log.e("HomeViewModel", "Error fetching user", e)
                }
            }
        }
    }

    private val trackerRepository = com.fitnessapp.repository.TrackerRepository()

    fun updateSteps(count: Int) {
        _steps.value = count
    }

    fun addWater() {
        val current = _waterIntake.value ?: 0
        if (current < 15) { // Max 15 glasses
            _waterIntake.value = current + 1
            saveData() // Save immediately for water
        }
    }
    
    fun saveData() {
        auth.currentUser?.let { user ->
            viewModelScope.launch {
                val date = java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault()).format(java.util.Date())
                trackerRepository.updateDailyStats(
                    user.uid,
                    date,
                    steps = _steps.value,
                    water = _waterIntake.value
                )
            }
        }
    }

    private fun generateAndSetPlan(user: User) {
        viewModelScope.launch {
             // Calculate BMI
            if (user.heightCm > 0 && user.weightKg > 0) {
                val heightM = user.heightCm / 100.0
                val bmiValue = user.weightKg / (heightM * heightM)
                val roundedBmi = Math.round(bmiValue * 10.0) / 10.0
                _bmi.postValue(roundedBmi)
            }

            // Generate plan on computation thread
            val plan = withContext(kotlinx.coroutines.Dispatchers.Default) {
                FitnessPlanGenerator.generatePlan(user)
            }
            
            _weeklyPlan.postValue(plan)
            
            // Get today's day of week
            val calendar = Calendar.getInstance()
            val day = calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.ENGLISH)?.lowercase()

            val workout = when (day) {
                "monday" -> plan.monday
                "tuesday" -> plan.tuesday
                "wednesday" -> plan.wednesday
                "thursday" -> plan.thursday
                "friday" -> plan.friday
                "saturday" -> plan.saturday
                "sunday" -> plan.sunday
                else -> null
            }
            _todaysWorkout.postValue(workout)
        }
    }
}
