@file:Suppress("DEPRECATION")

package com.example.time.app.receiver.alarmscreen

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.time.app.globalconstants.Constants.EXTRA_ALARM_ID
import com.example.time.domain.recevier.alarmscreen.AlarmReceiverHandler
import dagger.hilt.android.AndroidEntryPoint
import jakarta.inject.Inject

@Suppress("DEPRECATION")
@AndroidEntryPoint
class AlarmReceiver : BroadcastReceiver() {
    @Inject lateinit var handler: AlarmReceiverHandler

    override fun onReceive(
        context: Context,
        intent: Intent,
    ) {
        val alarmId = intent.getIntExtra(EXTRA_ALARM_ID, -1)
        val action = intent.action
        val extras = intent.extras

        Log.d("AlarmReceiver", "Received action: $action, alarmId: $alarmId")
        handler.handleAction(action, alarmId, extras)

        LocalBroadcastManager.getInstance(context).sendBroadcast(
            Intent(action).apply {
                putExtra(EXTRA_ALARM_ID, alarmId)
            },
        )
    }
}
