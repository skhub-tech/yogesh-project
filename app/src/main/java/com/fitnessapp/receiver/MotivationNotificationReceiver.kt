package com.fitnessapp.receiver

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.fitnessapp.ui.MainActivity
import com.fitnessapp.R
import com.fitnessapp.data.MotivationQuotes
import com.fitnessapp.utils.NotificationScheduler
import kotlin.random.Random

class MotivationNotificationReceiver : BroadcastReceiver() {
    
    companion object {
        const val CHANNEL_ID = "motivation_notifications"
        const val CHANNEL_NAME = "Motivation Reminders"
        const val NOTIFICATION_ID_BASE = 1000
    }
    
    override fun onReceive(context: Context, intent: Intent) {
        // Show notification with random motivational quote
        showNotification(context)
        
        // Re-schedule for tomorrow (will be handled by NotificationScheduler)
    }
    
    private fun showNotification(context: Context) {
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        
        // Create notification channel for Android O and above
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Motivational exercise reminders"
                enableVibration(true)
                enableLights(true)
            }
            notificationManager.createNotificationChannel(channel)
        }
        
        // Get random motivational quote
        val quote = MotivationQuotes.getRandomQuote()
        
        // Create intent to open app when notification is clicked
        val openAppIntent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        
        val pendingIntent = PendingIntent.getActivity(
            context,
            0,
            openAppIntent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )
        
        // Build notification
        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_fitness)
            .setContentTitle("Time to Move! 🏋️")
            .setContentText(quote)
            .setStyle(NotificationCompat.BigTextStyle().bigText(quote))
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .setVibrate(longArrayOf(0, 500, 200, 500))
            .build()
        
        // Show notification with unique ID
        val notificationId = NOTIFICATION_ID_BASE + Random.nextInt(1000)
        notificationManager.notify(notificationId, notification)
    }
}
