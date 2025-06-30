package com.example.time.data.service.alarmscreen

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.example.time.R
import com.example.time.app.globalconstants.Constants.ACTION_DISABLE_ALARM
import com.example.time.app.globalconstants.Constants.ACTION_SNOOZE_ALARM
import com.example.time.app.globalconstants.Constants.ACTION_TYPE
import com.example.time.app.globalconstants.Constants.ALARM_CHANEL
import com.example.time.app.globalconstants.Constants.EXTRA_ALARM_ID
import com.example.time.app.receiver.alarmscreen.AlarmReceiver
import com.example.time.domain.contract.alarmscreen.AlarmScreenNotificationCreator
import com.example.time.presentation.activity.alarmscreen.AlarmActivity
import dagger.hilt.android.qualifiers.ApplicationContext
import jakarta.inject.Inject

class AlarmScreenNotificationService @Inject constructor(
    @param:ApplicationContext private val context: Context
): AlarmScreenNotificationCreator {

    companion object{
        private const val DISABLE_ACTION_OFFSET = 10
        private const val SNOOZE_ACTION_OFFSET = 20
        private const val REQUEST_CODE_MULTIPLIER = 100
    }

    override fun create(alarmId: Int, alarmName: String, alarmDate: String): Notification {

        val channelId = ALARM_CHANEL
        val notificationManager = context.getSystemService(NotificationManager::class.java)

        val channel = NotificationChannel(
            channelId,
            context.getString(R.string.alarm_notifications),
            NotificationManager.IMPORTANCE_HIGH
        ).apply {
            setSound(null, null)
            enableVibration(false)
            setBypassDnd(true)
            lockscreenVisibility = Notification.VISIBILITY_PUBLIC
        }
        notificationManager.createNotificationChannel(channel)

        val fullScreenIntent = Intent(context, AlarmActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            putExtra(EXTRA_ALARM_ID, alarmId)
        }

        val pendingIntent = PendingIntent.getActivity(
            context,
            alarmId,
            fullScreenIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val disableIntent = Intent(context, AlarmReceiver::class.java).apply {
            action = ACTION_DISABLE_ALARM
            putExtra(EXTRA_ALARM_ID, alarmId)
            putExtra(ACTION_TYPE, ACTION_DISABLE_ALARM)
        }

        val disablePendingIntent = PendingIntent.getBroadcast(
            context,
            alarmId * REQUEST_CODE_MULTIPLIER + DISABLE_ACTION_OFFSET, // Unique requestCode
            disableIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val snoozeIntent = Intent(context, AlarmReceiver::class.java).apply {
            action = ACTION_SNOOZE_ALARM
            putExtra(EXTRA_ALARM_ID, alarmId)
            putExtra(ACTION_TYPE, ACTION_SNOOZE_ALARM)
        }

        val snoozePendingIntent = PendingIntent.getBroadcast(
            context,
            alarmId * REQUEST_CODE_MULTIPLIER + SNOOZE_ACTION_OFFSET, // Unique requestCode
            snoozeIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        return NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.ic_alarm_white)
            .setContentTitle(alarmName)
            .setContentText(alarmDate)
            .setPriority(NotificationCompat.PRIORITY_MAX)
            .setCategory(NotificationCompat.CATEGORY_ALARM)
            .setFullScreenIntent(pendingIntent, true)
            .setAutoCancel(false)
            .setOngoing(true)
            .setSound(null)
            .setVibrate(null)
            .addAction(R.drawable.ic_alarm_off_white, context.getString(R.string.disable), disablePendingIntent)
            .addAction(R.drawable.ic_postpone_white, context.getString(R.string.postpone), snoozePendingIntent)
            .build()
    }
}