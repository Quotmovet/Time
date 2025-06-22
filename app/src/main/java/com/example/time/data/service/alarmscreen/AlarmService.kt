package com.example.time.data.service.alarmscreen

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.media.RingtoneManager
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.time.R
import androidx.core.net.toUri
import com.example.time.presentation.activity.alarmscreen.AlarmActivity

class AlarmService : Service() {

    private var mediaPlayer: MediaPlayer? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val soundUriString = intent?.getStringExtra("EXTRA_SOUND_URI")
        val soundUri = soundUriString?.toUri()
            ?: RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)

        val alarmId = intent?.getIntExtra("EXTRA_ALARM_ID", -1)
        Log.d("AlarmService", "alarmId: $alarmId, soundUri: $soundUri")

        startForeground(1, createNotification(alarmId ?: -1))

        mediaPlayer = MediaPlayer().apply {
            try {
                setDataSource(this@AlarmService, soundUri)
                setAudioAttributes(
                    AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .setUsage(AudioAttributes.USAGE_ALARM)
                        .build()
                )
                isLooping = true
                prepare()
                start()
            } catch (e: Exception) {
                Log.e("AlarmService", "Failed to play alarm sound", e)
                stopSelf()
            }
        }

        return START_STICKY
    }

    override fun onDestroy() {
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? = null

    private fun createNotification(alarmId: Int): Notification {
        val channelId = "alarm_channel"
        val channelName = "Alarm Notifications"
        val notificationManager = getSystemService(NotificationManager::class.java)

        val channel = NotificationChannel(
            channelId,
            channelName,
            NotificationManager.IMPORTANCE_HIGH
        ).apply {
            enableLights(true)
            enableVibration(true)
            lockscreenVisibility = Notification.VISIBILITY_PUBLIC
        }

        notificationManager.createNotificationChannel(channel)

        val fullScreenIntent = Intent(this, AlarmActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or
                    Intent.FLAG_ACTIVITY_CLEAR_TOP or
                    Intent.FLAG_ACTIVITY_SINGLE_TOP
            putExtra("EXTRA_ALARM_ID", alarmId)
        }

        val fullScreenPendingIntent = PendingIntent.getActivity(
            this,
            0,
            fullScreenIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        return NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.ic_alarm)
            .setContentTitle("Будильник")
            .setContentText("Пора вставать!")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setCategory(NotificationCompat.CATEGORY_ALARM)
            .setFullScreenIntent(fullScreenPendingIntent, true)
            .setAutoCancel(true)
            .build()
    }
}