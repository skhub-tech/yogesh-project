package com.fitnessapp.utils

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import com.fitnessapp.receiver.MotivationNotificationReceiver
import java.util.Calendar

object NotificationScheduler {
    
    private const val TAG = "NotificationScheduler"
    private const val PREFS_NAME = "motivation_notifications"
    private const val KEY_ENABLED = "notification_enabled"
    private const val KEY_HOUR = "notification_hour"
    private const val KEY_MINUTE = "notification_minute"
    
    // Request codes for different notification times
    private val REQUEST_CODES = intArrayOf(1001, 1002, 1003, 1004, 1005, 1006)
    
    /**
     * Schedule notifications at 6 times around the target time:
     * -5 min, -3 min, -1 min, +1 min, +3 min, +5 min
     */
    fun scheduleNotifications(context: Context, hour: Int, minute: Int) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        
        // Check if we have permission to schedule exact alarms (Android 12+)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            if (!alarmManager.canScheduleExactAlarms()) {
                Log.w(TAG, "Cannot schedule exact alarms - permission not granted")
                return
            }
        }
        
        // Cancel any existing notifications first
        cancelNotifications(context)
        
        // Define the offset minutes: -5, -3, -1, +1, +3, +5
        val offsetMinutes = intArrayOf(-5, -3, -1, 1, 3, 5)
        
        offsetMinutes.forEachIndexed { index, offset ->
            val calendar = Calendar.getInstance().apply {
                timeInMillis = System.currentTimeMillis()
                set(Calendar.HOUR_OF_DAY, hour)
                set(Calendar.MINUTE, minute)
                set(Calendar.SECOND, 0)
                
                // Add offset
                add(Calendar.MINUTE, offset)
                
                // If time has passed for today, schedule for tomorrow
                if (timeInMillis <= System.currentTimeMillis()) {
                    add(Calendar.DAY_OF_MONTH, 1)
                }
            }
            
            val intent = Intent(context, MotivationNotificationReceiver::class.java)
            val pendingIntent = PendingIntent.getBroadcast(
                context,
                REQUEST_CODES[index],
                intent,
                PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
            )
            
            // Schedule exact alarm
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                alarmManager.setExactAndAllowWhileIdle(
                    AlarmManager.RTC_WAKEUP,
                    calendar.timeInMillis,
                    pendingIntent
                )
            } else {
                alarmManager.setExact(
                    AlarmManager.RTC_WAKEUP,
                    calendar.timeInMillis,
                    pendingIntent
                )
            }
            
            Log.d(TAG, "Scheduled notification ${index + 1} at ${calendar.time}")
        }
        
        // Save preferences
        savePreferences(context, true, hour, minute)
        
        Log.d(TAG, "All 6 notifications scheduled successfully for $hour:$minute")
    }
    
    /**
     * Cancel all scheduled notifications
     */
    fun cancelNotifications(context: Context) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        
        REQUEST_CODES.forEach { requestCode ->
            val intent = Intent(context, MotivationNotificationReceiver::class.java)
            val pendingIntent = PendingIntent.getBroadcast(
                context,
                requestCode,
                intent,
                PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_NO_CREATE
            )
            
            pendingIntent?.let {
                alarmManager.cancel(it)
                it.cancel()
            }
        }
        
        // Update preferences
        savePreferences(context, false, 0, 0)
        
        Log.d(TAG, "All notifications cancelled")
    }
    
    /**
     * Check if notifications are currently enabled
     */
    fun isEnabled(context: Context): Boolean {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return prefs.getBoolean(KEY_ENABLED, false)
    }
    
    /**
     * Get saved notification time
     */
    fun getScheduledTime(context: Context): Pair<Int, Int>? {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        if (!prefs.getBoolean(KEY_ENABLED, false)) return null
        
        val hour = prefs.getInt(KEY_HOUR, -1)
        val minute = prefs.getInt(KEY_MINUTE, -1)
        
        return if (hour >= 0 && minute >= 0) Pair(hour, minute) else null
    }
    
    /**
     * Get formatted time string
     */
    fun getFormattedTime(context: Context): String {
        val time = getScheduledTime(context) ?: return "Not set"
        val (hour, minute) = time
        
        val amPm = if (hour >= 12) "PM" else "AM"
        val displayHour = if (hour > 12) hour - 12 else if (hour == 0) 12 else hour
        
        return String.format("%d:%02d %s", displayHour, minute, amPm)
    }
    
    private fun savePreferences(context: Context, enabled: Boolean, hour: Int, minute: Int) {
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).edit().apply {
            putBoolean(KEY_ENABLED, enabled)
            putInt(KEY_HOUR, hour)
            putInt(KEY_MINUTE, minute)
            apply()
        }
    }
}
