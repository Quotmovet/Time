package com.example.time.app.receiver.alarmscreen

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.content.ContextCompat
import com.example.time.app.globalconstants.Constants.EXTRA_ALARM_ID
import com.example.time.app.globalconstants.Constants.EXTRA_ALARM_NAME
import com.example.time.app.globalconstants.Constants.EXTRA_SOUND_URI
import com.example.time.presentation.service.alarmscreen.AlarmService

class AlarmReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        Log.d("AlarmReceiver", "Received alarm broadcast")

        val alarmId = intent.getIntExtra(EXTRA_ALARM_ID, -1)
        val soundUri = intent.getStringExtra(EXTRA_SOUND_URI)
        val alarmName = intent.getStringExtra(EXTRA_ALARM_NAME)

        if (alarmId == -1 || soundUri == null || alarmName == null) {
            Log.e("AlarmReceiver", "Invalid alarm data received")
            return
        }

        val alarmServiceIntent = Intent(context, AlarmService::class.java).apply {
            putExtra(EXTRA_ALARM_ID, alarmId)
            putExtra(EXTRA_SOUND_URI, soundUri)
            putExtra(EXTRA_ALARM_NAME, alarmName)
        }

        ContextCompat.startForegroundService(context, alarmServiceIntent)
        Log.d("AlarmReceiver", "AlarmService started")
    }
}
