package com.fitnessapp.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.media.RingtoneManager
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.fitnessapp.R

class AlarmSoundService : Service() {
    
    private var mediaPlayer: MediaPlayer? = null
    
    companion object {
        const val CHANNEL_ID = "gym_alarm_sound"
        const val NOTIFICATION_ID = 2000
        const val ACTION_STOP_ALARM = "com.fitnessapp.STOP_ALARM"
    }
    
    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }
    
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            ACTION_STOP_ALARM -> {
                stopAlarm()
                showFollowUpNotification()
                stopSelf()
            }
            else -> {
                startForegroundWithNotification()
                playAlarmSound()
            }
        }
        return START_NOT_STICKY
    }
    
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Gym Alarm",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Alarm sound for gym reminder"
                setSound(null, null) // We'll play our own sound
            }
            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }
    }
    
    private fun startForegroundWithNotification() {
        val notification = createAlarmNotification()
        startForeground(NOTIFICATION_ID, notification)
    }
    
    private fun createAlarmNotification(): Notification {
        // Create intent to stop alarm
        val stopIntent = Intent(this, AlarmSoundService::class.java).apply {
            action = ACTION_STOP_ALARM
        }
        val stopPendingIntent = PendingIntent.getService(
            this,
            0,
            stopIntent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )
        
        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_fitness)
            .setContentTitle("It's time to start your exercise or gym! 🏋️")
            .setContentText("Don't miss your workout today!")
            .setPriority(NotificationCompat.PRIORITY_MAX)
            .setCategory(NotificationCompat.CATEGORY_ALARM)
            .setOngoing(true)
            .setAutoCancel(false)
            .addAction(
                R.drawable.ic_fitness,
                "It's time to start",
                stopPendingIntent
            )
            .setVibrate(longArrayOf(0, 1000, 500, 1000))
            .build()
    }
    
    private fun playAlarmSound() {
        try {
            // Get default alarm ringtone
            val alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
                ?: RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE)
            
            mediaPlayer = MediaPlayer().apply {
                setDataSource(this@AlarmSoundService, alarmUri)
                
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    setAudioAttributes(
                        AudioAttributes.Builder()
                            .setUsage(AudioAttributes.USAGE_ALARM)
                            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                            .build()
                    )
                } else {
                    @Suppress("DEPRECATION")
                    setAudioStreamType(android.media.AudioManager.STREAM_ALARM)
                }
                
                isLooping = true
                prepare()
                start()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    
    private fun stopAlarm() {
        mediaPlayer?.let {
            if (it.isPlaying) {
                it.stop()
            }
            it.release()
        }
        mediaPlayer = null
    }
    
    private fun showFollowUpNotification() {
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        
        val followUpNotification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_fitness)
            .setContentTitle("Great Decision! 💪")
            .setContentText("I hope you start your exercise. You've got this!")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
            .setTimeoutAfter(5000) // Auto-dismiss after 5 seconds
            .build()
        
        notificationManager.notify(2001, followUpNotification)
    }
    
    override fun onDestroy() {
        stopAlarm()
        super.onDestroy()
    }
    
    override fun onBind(intent: Intent?): IBinder? = null
}
