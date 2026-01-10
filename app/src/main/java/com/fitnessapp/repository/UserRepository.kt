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
                try {
                    // Wait up to 5 seconds for server confirmation
                    kotlinx.coroutines.withTimeout(5000L) {
                        db.collection("users").document(user.uid).set(user).await()
                    }
                    android.util.Log.d("UserRepository", "User saved successfully (synced)")
                } catch (e: kotlinx.coroutines.TimeoutCancellationException) {
                    android.util.Log.w("UserRepository", "Save timed out, assuming offline/background sync")
                    // Note: The Firestore SDK continues to retry in the background.
                    // We return success so the UI doesn't block.
                }
            }
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
