package com.fitnessapp.ui.settings

import android.Manifest
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.fitnessapp.R
import com.fitnessapp.utils.NotificationScheduler
import com.fitnessapp.utils.GymAlarmScheduler
import com.google.android.material.card.MaterialCardView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.util.Calendar

class SettingsFragment : Fragment(R.layout.fragment_settings) {
    
    private lateinit var cardMotivationNotifications: MaterialCardView
    private lateinit var tvNotificationStatus: TextView
    private lateinit var cardGymReminder: MaterialCardView
    private lateinit var tvGymAlarmStatus: TextView
    
    // Request notification permission (Android 13+)
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            showTimePicker()
        } else {
            Toast.makeText(context, "Notification permission denied", Toast.LENGTH_SHORT).show()
        }
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        cardMotivationNotifications = view.findViewById(R.id.cardMotivationNotifications)
        tvNotificationStatus = view.findViewById(R.id.tvNotificationStatus)
        cardGymReminder = view.findViewById(R.id.cardGymReminder)
        tvGymAlarmStatus = view.findViewById(R.id.tvGymAlarmStatus)
        
        // Update status text
        updateStatusText()
        updateGymAlarmStatusText()
        
        // Handle card click
        cardMotivationNotifications.setOnClickListener {
            showNotificationDialog()
        }
        
        // Handle gym reminder click
        cardGymReminder.setOnClickListener {
            showGymAlarmDialog()
        }
    }
    
    private fun updateStatusText() {
        val formattedTime = context?.let { NotificationScheduler.getFormattedTime(it) } ?: "Not set"
        tvNotificationStatus.text = if (formattedTime == "Not set") {
            "Tap to set reminder time"
        } else {
            "Daily at $formattedTime"
        }
    }
    
    private fun showNotificationDialog() {
        context?.let { ctx ->
            val isEnabled = NotificationScheduler.isEnabled(ctx)
            
            MaterialAlertDialogBuilder(ctx)
                .setTitle("Motivation Notifications")
                .setMessage("Get daily motivational reminders to exercise! Set a time and we'll send you inspiring messages to keep you motivated.")
                .setPositiveButton(if (isEnabled) "Change Time" else "Set Time") { _, _ ->
                    checkPermissionAndShowTimePicker()
                }
                .apply {
                    if (isEnabled) {
                        setNegativeButton("Turn Off") { _, _ ->
                            NotificationScheduler.cancelNotifications(ctx)
                            updateStatusText()
                            Toast.makeText(ctx, "Notifications turned off", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                .setNeutralButton("Cancel", null)
                .show()
        }
    }
    
    private fun checkPermissionAndShowTimePicker() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            // Android 13+ requires runtime permission
            when {
                ContextCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.POST_NOTIFICATIONS
                ) == PackageManager.PERMISSION_GRANTED -> {
                    showTimePicker()
                }
                shouldShowRequestPermissionRationale(Manifest.permission.POST_NOTIFICATIONS) -> {
                    // Show rationale
                    MaterialAlertDialogBuilder(requireContext())
                        .setTitle("Permission Required")
                        .setMessage("Notification permission is needed to send you motivational reminders.")
                        .setPositiveButton("Grant") { _, _ ->
                            requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                        }
                        .setNegativeButton("Cancel", null)
                        .show()
                }
                else -> {
                    requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                }
            }
        } else {
            // Below Android 13, no runtime permission needed
            showTimePicker()
        }
    }
    
    private fun showTimePicker() {
        val calendar = Calendar.getInstance()
        val currentHour = calendar.get(Calendar.HOUR_OF_DAY)
        val currentMinute = calendar.get(Calendar.MINUTE)
        
        TimePickerDialog(
            requireContext(),
            { _, hour, minute ->
                scheduleNotifications(hour, minute)
            },
            currentHour,
            currentMinute,
            false // 12-hour format
        ).show()
    }
    
    private fun scheduleNotifications(hour: Int, minute: Int) {
        context?.let { ctx ->
            NotificationScheduler.scheduleNotifications(ctx, hour, minute)
            updateStatusText()
            
            val amPm = if (hour >= 12) "PM" else "AM"
            val displayHour = if (hour > 12) hour - 12 else if (hour == 0) 12 else hour
            val timeString = String.format("%d:%02d %s", displayHour, minute, amPm)
            
            Toast.makeText(
                ctx,
                "Notifications scheduled for $timeString ✅",
                Toast.LENGTH_LONG
            ).show()
        }
    }
    
    // Gym Alarm Methods
    private fun updateGymAlarmStatusText() {
        val formattedTime = context?.let { GymAlarmScheduler.getFormattedTime(it) } ?: "Not set"
        tvGymAlarmStatus.text = if (formattedTime == "Not set") {
            "Tap to set gym time"
        } else {
            "Daily at $formattedTime"
        }
    }
    
    private fun showGymAlarmDialog() {
        context?.let { ctx ->
            val isEnabled = GymAlarmScheduler.isEnabled(ctx)
            
            MaterialAlertDialogBuilder(ctx)
                .setTitle("Exercise Reminder")
                .setMessage("Set your daily exercise/gym time. You'll get an alarm reminder to start your workout!")
                .setPositiveButton(if (isEnabled) "Change Time" else "Set Time") { _, _ ->
                    showGymTimePicker()
                }
                .apply {
                    if (isEnabled) {
                        setNegativeButton("Turn Off") { _, _ ->
                            GymAlarmScheduler.cancelAlarm(ctx)
                            updateGymAlarmStatusText()
                            Toast.makeText(ctx, "Gym alarm turned off", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                .setNeutralButton("Cancel", null)
                .show()
        }
    }
    
    private fun showGymTimePicker() {
        val calendar = Calendar.getInstance()
        val currentHour = calendar.get(Calendar.HOUR_OF_DAY)
        val currentMinute = calendar.get(Calendar.MINUTE)
        
        TimePickerDialog(
            requireContext(),
            { _, hour, minute ->
                scheduleGymAlarm(hour, minute)
            },
            currentHour,
            currentMinute,
            false // 12-hour format
        ).show()
    }
    
    private fun scheduleGymAlarm(hour: Int, minute: Int) {
        context?.let { ctx ->
            // Check if we can schedule exact alarms (Android 12+)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                val alarmManager = ctx.getSystemService(Context.ALARM_SERVICE) as android.app.AlarmManager
                if (!alarmManager.canScheduleExactAlarms()) {
                    // Show dialog to user about permission
                    MaterialAlertDialogBuilder(ctx)
                        .setTitle("Permission Required")
                        .setMessage("Android 12+ requires permission to schedule exact alarms. Please enable 'Alarms & reminders' permission in Settings.")
                        .setPositiveButton("Open Settings") { _, _ ->
                            try {
                                val intent = Intent(android.provider.Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM)
                                startActivity(intent)
                            } catch (e: Exception) {
                                Toast.makeText(ctx, "Please enable alarm permission in Settings", Toast.LENGTH_LONG).show()
                            }
                        }
                        .setNegativeButton("Cancel", null)
                        .show()
                    return
                }
            }
            
            GymAlarmScheduler.scheduleAlarm(ctx, hour, minute)
            updateGymAlarmStatusText()
            
            val amPm = if (hour >= 12) "PM" else "AM"
            val displayHour = if (hour > 12) hour - 12 else if (hour == 0) 12 else hour
            val timeString = String.format("%d:%02d %s", displayHour, minute, amPm)
            
            Toast.makeText(
                ctx,
                "Gym alarm set for $timeString ⏰",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}
