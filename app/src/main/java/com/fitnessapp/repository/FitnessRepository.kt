package com.fitnessapp.repository

import com.fitnessapp.model.Exercise
import com.fitnessapp.model.WeeklySchedule
import com.fitnessapp.services.FitnessPlanGenerator
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import com.fitnessapp.model.User

class FitnessRepository {
    private val db = FirebaseFirestore.getInstance()

    // Exercise Library
    suspend fun getExercises(): Result<List<Exercise>> {
        return try {
            val snapshot = db.collection("exercises").get().await()
            val exercises = snapshot.toObjects(Exercise::class.java)
            Result.success(exercises)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun addExercise(exercise: Exercise): Result<Boolean> {
        return try {
            db.collection("exercises").document(exercise.id).set(exercise).await()
            Result.success(true)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // Workout Plan
    suspend fun saveWorkoutPlan(uid: String, plan: WeeklySchedule): Result<Boolean> {
        return try {
            db.collection("users").document(uid).collection("plans").document("current_plan")
                .set(plan)
                .await()
            Result.success(true)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getWorkoutPlan(uid: String): Result<WeeklySchedule?> {
        return try {
            val snapshot = db.collection("users").document(uid).collection("plans").document("current_plan")
                .get()
                .await()
            val plan = snapshot.toObject(WeeklySchedule::class.java)
            Result.success(plan)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    fun generatePlan(user: User): WeeklySchedule {
        return FitnessPlanGenerator.generatePlan(user)
    }
}
