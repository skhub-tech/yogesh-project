package com.fitnessapp.repository

import com.fitnessapp.model.DailyLog
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class TrackerRepository {
    private val db = FirebaseFirestore.getInstance()

    suspend fun saveDailyLog(uid: String, log: DailyLog): Result<Boolean> {
        return try {
            // Document ID is the date (YYYY-MM-DD)
            db.collection("users").document(uid).collection("logs").document(log.date)
                .set(log)
                .await()
            Result.success(true)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getDailyLog(uid: String, date: String): Result<DailyLog?> {
        return try {
            val snapshot = db.collection("users").document(uid).collection("logs").document(date)
                .get()
                .await()
            val log = snapshot.toObject(DailyLog::class.java)
            Result.success(log)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun getLast7DaysLogs(uid: String): Result<List<DailyLog>> {
        return try {
             val snapshot = db.collection("users").document(uid).collection("logs")
                .orderBy("date", com.google.firebase.firestore.Query.Direction.DESCENDING)
                .limit(7)
                .get()
                .await()
            val logs = snapshot.toObjects(DailyLog::class.java)
            Result.success(logs)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    suspend fun updateDailyStats(uid: String, date: String, steps: Int? = null, water: Int? = null, weight: Double? = null): Result<Boolean> {
        return try {
            val docRef = db.collection("users").document(uid).collection("logs").document(date)
            // Check if exists first, if not create
            val snapshot = docRef.get().await()
            if (!snapshot.exists()) {
                 val initialLog = DailyLog(
                     date = date, 
                     steps = steps ?: 0, 
                     waterIntakeMl = water ?: 0,
                     weight = weight ?: 0.0
                 )
                 docRef.set(initialLog).await()
            } else {
                val updates = mutableMapOf<String, Any>()
                if (steps != null) updates["steps"] = steps
                if (water != null) updates["waterIntakeMl"] = water
                if (weight != null) updates["weight"] = weight
                
                if (updates.isNotEmpty()) {
                    docRef.update(updates).await()
                }
            }
            Result.success(true)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun logExerciseSet(uid: String, log: com.fitnessapp.model.ExerciseSetLog): Result<Boolean> {
        return try {
            db.collection("users").document(uid).collection("workout_logs").add(log).await()
            Result.success(true)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getWorkoutLogs(uid: String): Result<List<com.fitnessapp.model.ExerciseSetLog>> {
        return try {
            val snapshot = db.collection("users").document(uid).collection("workout_logs")
                .orderBy("timestamp", com.google.firebase.firestore.Query.Direction.DESCENDING)
                .get()
                .await()
            val logs = snapshot.toObjects(com.fitnessapp.model.ExerciseSetLog::class.java)
            Result.success(logs)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
