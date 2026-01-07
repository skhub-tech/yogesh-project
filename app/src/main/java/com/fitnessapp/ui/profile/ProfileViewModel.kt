package com.fitnessapp.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fitnessapp.model.User
import com.fitnessapp.repository.UserRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {

    private val repository = UserRepository()
    private val auth = FirebaseAuth.getInstance()

    private val _user = MutableLiveData<User?>()
    val user: LiveData<User?> get() = _user

    private val _saveStatus = MutableLiveData<Result<Boolean>>()
    val saveStatus: LiveData<Result<Boolean>> get() = _saveStatus

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    init {
        loadUser()
    }

    private fun loadUser() {
        val uid = auth.currentUser?.uid
        if (uid != null) {
            _isLoading.value = true
            viewModelScope.launch {
                val result = repository.getUser(uid)
                if (result.isSuccess) {
                    _user.value = result.getOrNull()
                }
                _isLoading.value = false
            }
        }
    }

    fun saveUserProfile(age: Int, gender: String, height: Double, weight: Double, goal: String) {
        val currentUser = auth.currentUser
        if (currentUser == null) {
            _saveStatus.value = Result.failure(Exception("User not logged in"))
            return
        }

        _isLoading.value = true

        val user = User(
            uid = currentUser.uid,
            name = currentUser.displayName ?: "User",
            email = currentUser.email ?: "",
            age = age,
            gender = gender,
            heightCm = height,
            weightKg = weight,
            fitnessGoal = goal
        )

        viewModelScope.launch {
            val result = repository.saveUser(user)
            _saveStatus.value = result
            _isLoading.value = false
        }
    }
}
