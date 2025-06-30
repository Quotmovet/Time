package com.example.time.domain.recevier.alarmscreen

import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import com.example.time.app.annotation.IoDispatcher
import com.example.time.app.globalconstants.Constants.ACTION_DISABLE_ALARM
import com.example.time.app.globalconstants.Constants.ACTION_SNOOZE_ALARM
import com.example.time.app.globalconstants.Constants.EXTRA_ALARM_DATE
import com.example.time.app.globalconstants.Constants.EXTRA_ALARM_ID
import com.example.time.app.globalconstants.Constants.EXTRA_ALARM_NAME
import com.example.time.app.globalconstants.Constants.EXTRA_SOUND_URI
import com.example.time.domain.repositories.alarmscreen.AlarmScreenRepository
import com.example.time.domain.scheduler.alarmscreen.AlarmScreenScheduler
import com.example.time.presentation.service.alarmscreen.AlarmService
import dagger.hilt.android.qualifiers.ApplicationContext
import jakarta.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AlarmReceiverHandler @Inject constructor(
    private val repository: AlarmScreenRepository,
    private val scheduler: AlarmScreenScheduler,
    @param:ApplicationContext private val context: Context,
    @param:IoDispatcher private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    companion object {
        private const val FIVE_IN_MILLIS = 5
        private const val SIXTY_IN_MILLIS = 60
        private const val ONE_THOUSAND_IN_MILLIS = 1000L
    }

    fun handleAction(action: String?, alarmId: Int, extras: Bundle?) {
        when (action) {
            ACTION_DISABLE_ALARM -> disableAlarm(alarmId)
            ACTION_SNOOZE_ALARM -> snoozeAlarm(alarmId)
            else -> triggerAlarm(alarmId, extras)
        }
    }

    private fun disableAlarm(alarmId: Int) {
        if (alarmId == -1) return

        stopAlarmService()
        cancelNotification(alarmId)

        CoroutineScope(dispatcher).launch {
            val alarm = runCatching { repository.getAlarmById(alarmId) }.getOrNull()
            alarm?.let {
                Log.d("AlarmReceiverHandler", "Alarm $alarmId disabled")
            } ?: Log.e("AlarmReceiverHandler", "Alarm $alarmId not found")
        }
    }

    private fun snoozeAlarm(alarmId: Int) {
        if (alarmId == -1) return

        stopAlarmService()
        cancelNotification(alarmId)

        CoroutineScope(dispatcher).launch {
            val alarm = runCatching { repository.getAlarmById(alarmId) }.getOrNull()
            alarm?.let {
                val delayMillis = FIVE_IN_MILLIS * SIXTY_IN_MILLIS * ONE_THOUSAND_IN_MILLIS
                scheduler.schedulePostponeOnce(it, System.currentTimeMillis() + delayMillis)
                Log.d("AlarmReceiverHandler", "Alarm $alarmId snoozed for 5 min")
            } ?: Log.e("AlarmReceiverHandler", "Alarm $alarmId not found")
        }
    }

    private fun triggerAlarm(alarmId: Int, extras: Bundle?) {
        val soundUri = extras?.getString(EXTRA_SOUND_URI)
        val alarmName = extras?.getString(EXTRA_ALARM_NAME)
        val date = extras?.getString(EXTRA_ALARM_DATE)

        if (alarmId == -1 || soundUri == null || alarmName == null) {
            Log.e("AlarmReceiverHandler", "Invalid alarm data received")
            return
        }

        val intent = Intent(context, AlarmService::class.java).apply {
            putExtra(EXTRA_ALARM_ID, alarmId)
            putExtra(EXTRA_SOUND_URI, soundUri)
            putExtra(EXTRA_ALARM_NAME, alarmName)
            putExtra(EXTRA_ALARM_DATE, date)
        }

        ContextCompat.startForegroundService(context, intent)
        Log.d("AlarmReceiverHandler", "AlarmService started for alarm $alarmId")
    }

    private fun stopAlarmService() {
        val stopServiceIntent = Intent(context, AlarmService::class.java)
        context.stopService(stopServiceIntent)
    }

    private fun cancelNotification(alarmId: Int) {
        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.cancel(alarmId)
    }
}