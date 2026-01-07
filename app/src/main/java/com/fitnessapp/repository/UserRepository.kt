package com.fitnessapp.repository

import com.fitnessapp.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class UserRepository {
    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()

    suspend fun saveUser(user: User): Result<Boolean> {
        return try {
            android.util.Log.d("UserRepository", "Saving user: ${user.uid}")
            kotlinx.coroutines.withContext(kotlinx.coroutines.Dispatchers.IO) {
                // Add a timeout of 10 seconds
                kotlinx.coroutines.withTimeout(10000L) {
                    db.collection("users").document(user.uid).set(user).await()
                }
            }
            android.util.Log.d("UserRepository", "User saved successfully")
            Result.success(true)
        } catch (e: Exception) {
            android.util.Log.e("UserRepository", "Error saving user", e)
            Result.failure(e)
        }
    }

    suspend fun getUser(uid: String): Result<User?> {
        return try {
            val snapshot = db.collection("users").document(uid).get().await()
            val user = snapshot.toObject(User::class.java)
            Result.success(user)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    fun getCurrentUserUid(): String? {
        return auth.currentUser?.uid
    }
}
