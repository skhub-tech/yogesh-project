package com.fitnessapp.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import com.fitnessapp.service.AlarmSoundService
import com.fitnessapp.utils.GymAlarmScheduler

class GymAlarmReceiver : BroadcastReceiver() {
    
    companion object {
        private const val TAG = "GymAlarmReceiver"
    }
    
    override fun onReceive(context: Context, intent: Intent) {
        Log.d(TAG, "Gym alarm triggered! Starting alarm service...")
        
        // Start the alarm sound service
        val serviceIntent = Intent(context, AlarmSoundService::class.java)
        
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                context.startForegroundService(serviceIntent)
                Log.d(TAG, "Started foreground service")
            } else {
                context.startService(serviceIntent)
                Log.d(TAG, "Started service")
            }
        } catch (e: Exception) {
            Log.e(TAG, "Failed to start alarm service", e)
        }
        
        // Reschedule for next day
        val scheduledTime = GymAlarmScheduler.getScheduledTime(context)
        if (scheduledTime != null) {
            val (hour, minute) = scheduledTime
            Log.d(TAG, "Rescheduling alarm for tomorrow at $hour:$minute")
            GymAlarmScheduler.scheduleAlarm(context, hour, minute)
        }
    }
}
