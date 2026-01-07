package com.fitnessapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fitnessapp.R

import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        // Define top-level destinations (showing hamburger icon instead of back arrow)
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.homeFragment, 
                R.id.exercisesFragment,
                R.id.dietFragment,
                R.id.trackerFragment,
                R.id.profileFragment, 
                R.id.settingsFragment, 
                R.id.aboutFragment,
                R.id.sparkyFragment
            ), drawerLayout
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        
        val bottomNav = findViewById<com.google.android.material.bottomnavigation.BottomNavigationView>(R.id.bottom_nav_view)
        bottomNav.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.loginFragment || destination.id == R.id.setupProfileFragment) {
                supportActionBar?.hide()
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
                bottomNav.visibility = android.view.View.GONE
            } else {
                supportActionBar?.show()
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
                bottomNav.visibility = android.view.View.VISIBLE
            }
        }
        
        setupAuthListener(navView)
        scheduleDailyNotification()
    }

    private fun setupAuthListener(navView: NavigationView) {
        com.google.firebase.auth.FirebaseAuth.getInstance().addAuthStateListener { auth ->
            val user = auth.currentUser
            val headerView = navView.getHeaderView(0)
            val navUsername = headerView.findViewById<android.widget.TextView>(R.id.tv_user_name)
            val navUserEmail = headerView.findViewById<android.widget.TextView>(R.id.tv_user_email)
            val navUserImage = headerView.findViewById<android.widget.ImageView>(R.id.imageView)

            if (user != null) {
                navUsername.text = user.displayName ?: "User"
                navUserEmail.text = user.email ?: ""
                
                user.photoUrl?.let { url ->
                    com.bumptech.glide.Glide.with(this)
                        .load(url)
                        .circleCrop()
                        .placeholder(R.drawable.ic_person)
                        .error(R.drawable.ic_person)
                        .into(navUserImage)
                }
            } else {
                 navUsername.text = "Fitness User"
                 navUserEmail.text = "Please Login"
                 navUserImage.setImageResource(R.drawable.ic_person)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun scheduleDailyNotification() {
        val alarmManager = getSystemService(android.content.Context.ALARM_SERVICE) as android.app.AlarmManager
        val intent = android.content.Intent(this, com.fitnessapp.receivers.NotificationReceiver::class.java).apply {
            putExtra("title", "Fitness App")
            putExtra("message", "Time for your daily workout!")
        }
        val pendingIntent = android.app.PendingIntent.getBroadcast(
            this,
            0,
            intent,
            android.app.PendingIntent.FLAG_IMMUTABLE or android.app.PendingIntent.FLAG_UPDATE_CURRENT
        )

        val calendar = java.util.Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(java.util.Calendar.HOUR_OF_DAY, 8) // 8 AM
            set(java.util.Calendar.MINUTE, 0)
            set(java.util.Calendar.SECOND, 0)
        }

        if (calendar.timeInMillis <= System.currentTimeMillis()) {
             calendar.add(java.util.Calendar.DAY_OF_YEAR, 1)
        }

        try {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
                if (alarmManager.canScheduleExactAlarms()) {
                     alarmManager.setExactAndAllowWhileIdle(
                        android.app.AlarmManager.RTC_WAKEUP,
                        calendar.timeInMillis,
                        pendingIntent
                    )
                } else {
                     // Fallback to inexact
                     alarmManager.setInexactRepeating(
                        android.app.AlarmManager.RTC_WAKEUP,
                        calendar.timeInMillis,
                        android.app.AlarmManager.INTERVAL_DAY,
                        pendingIntent
                    )
                }
            } else {
                alarmManager.setExactAndAllowWhileIdle(
                    android.app.AlarmManager.RTC_WAKEUP,
                    calendar.timeInMillis,
                    pendingIntent
                )
            }
        } catch (e: SecurityException) {
             e.printStackTrace()
        }
    }
}
