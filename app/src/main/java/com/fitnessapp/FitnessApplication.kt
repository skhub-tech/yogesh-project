package com.fitnessapp

import android.app.Application
import com.google.firebase.FirebaseApp

class FitnessApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Initialize Firebase
        FirebaseApp.initializeApp(this)
    }
}
