package com.example.time.data.receiver.alarmscreen

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat
import com.example.time.data.service.alarmscreen.AlarmService

class AlarmReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val soundUri = intent.getStringExtra("EXTRA_SOUND_URI")
        val alarmIntent = Intent(context, AlarmService::class.java).apply {
            putExtra("EXTRA_SOUND_URI", soundUri)
        }
        ContextCompat.startForegroundService(context, alarmIntent)
    }
}