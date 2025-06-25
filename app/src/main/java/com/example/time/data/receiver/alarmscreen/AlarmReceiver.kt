package com.example.time.data.receiver.alarmscreen

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.content.ContextCompat
import com.example.time.data.service.alarmscreen.AlarmService
import com.example.time.presentation.activity.alarmscreen.AlarmActivity
import com.example.time.presentation.common.util.Constants.EXTRA_ALARM_ID
import com.example.time.presentation.common.util.Constants.EXTRA_ALARM_NAME
import com.example.time.presentation.common.util.Constants.EXTRA_SOUND_URI

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        Log.d("AlarmReceiver", "Received alarm broadcast")

        val soundUri = intent.getStringExtra(EXTRA_SOUND_URI)
        val alarmId = intent.getIntExtra(EXTRA_ALARM_ID, -1)
        val alarmName = intent.getStringExtra(EXTRA_ALARM_NAME)
        Log.d("AlarmReceiver", "alarmId: $alarmId, soundUri: $soundUri, alarmName: $alarmName")

        // Start AlarmService
        val alarmIntent = Intent(context, AlarmService::class.java).apply {
            putExtra(EXTRA_SOUND_URI, soundUri)
            putExtra(EXTRA_ALARM_ID, alarmId)
            putExtra(EXTRA_ALARM_NAME, alarmName)
        }
        ContextCompat.startForegroundService(context, alarmIntent)
        Log.d("AlarmReceiver", "AlarmService started")

        // Start AlarmActivity
        val activityIntent = Intent(context, AlarmActivity::class.java).apply {
            putExtra(EXTRA_ALARM_ID, alarmId)
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or
                    Intent.FLAG_ACTIVITY_CLEAR_TOP or
                    Intent.FLAG_ACTIVITY_SINGLE_TOP
        }
        try {
            context.startActivity(activityIntent)
            Log.d("AlarmReceiver", "AlarmActivity started")
        } catch (e: Exception) {
            Log.e("AlarmReceiver", "Failed to start AlarmActivity", e)
        }
    }
}
