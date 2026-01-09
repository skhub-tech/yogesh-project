package com.fitnessapp.utils

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import com.fitnessapp.receiver.GymAlarmReceiver
import java.util.Calendar

object GymAlarmScheduler {
    
    private const val TAG = "GymAlarmScheduler"
    private const val PREFS_NAME = "gym_alarm"
    private const val KEY_ENABLED = "alarm_enabled"
    private const val KEY_HOUR = "alarm_hour"
    private const val KEY_MINUTE = "alarm_minute"
    private const val REQUEST_CODE = 3000
    
    /**
     * Schedule daily gym alarm at specified time
     */
    fun scheduleAlarm(context: Context, hour: Int, minute: Int) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        
        // Check permission for exact alarms (Android 12+)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            if (!alarmManager.canScheduleExactAlarms()) {
                Log.e(TAG, "Cannot schedule exact alarms - permission not granted")
                // On Android 12+, need to request permission
                // User needs to grant it in Settings
                return
            }
        }
        
        // Cancel existing alarm first
        cancelAlarm(context)
        
        val calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, minute)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
            
            // If time has passed for today, schedule for tomorrow
            if (timeInMillis <= System.currentTimeMillis()) {
                add(Calendar.DAY_OF_MONTH, 1)
            }
        }
        
        val intent = Intent(context, GymAlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            REQUEST_CODE,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )
        
        // Schedule exact alarm (one-time, will be rescheduled by receiver for next day)
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                // setExactAndAllowWhileIdle bypasses doze mode
                alarmManager.setExactAndAllowWhileIdle(
                    AlarmManager.RTC_WAKEUP,
                    calendar.timeInMillis,
                    pendingIntent
                )
                Log.d(TAG, "Scheduled using setExactAndAllowWhileIdle")
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                // setExact for exact timing on Android 4.4+
                alarmManager.setExact(
                    AlarmManager.RTC_WAKEUP,
                    calendar.timeInMillis,
                    pendingIntent
                )
                Log.d(TAG, "Scheduled using setExact")
            } else {
                // Fallback for older versions
                alarmManager.set(
                    AlarmManager.RTC_WAKEUP,
                    calendar.timeInMillis,
                    pendingIntent
                )
                Log.d(TAG, "Scheduled using set()")
            }
            
            // Save preferences
            savePreferences(context, true, hour, minute)
            
            Log.d(TAG, "Gym alarm scheduled successfully for $hour:$minute")
            Log.d(TAG, "Next alarm time: ${calendar.time}")
            Log.d(TAG, "Current time: ${Calendar.getInstance().time}")
            Log.d(TAG, "Time until alarm: ${(calendar.timeInMillis - System.currentTimeMillis()) / 1000 / 60} minutes")
        } catch (e: Exception) {
            Log.e(TAG, "Failed to schedule alarm", e)
        }
    }
    
    /**
     * Cancel scheduled gym alarm
     */
    fun cancelAlarm(context: Context) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        
        val intent = Intent(context, GymAlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            REQUEST_CODE,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_NO_CREATE
        )
        
        pendingIntent?.let {
            alarmManager.cancel(it)
            it.cancel()
        }
        
        // Update preferences
        savePreferences(context, false, 0, 0)
        
        Log.d(TAG, "Gym alarm cancelled")
    }
    
    /**
     * Check if alarm is currently enabled
     */
    fun isEnabled(context: Context): Boolean {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return prefs.getBoolean(KEY_ENABLED, false)
    }
    
    /**
     * Get scheduled alarm time
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
