@file:Suppress("DEPRECATION")

package com.example.time.presentation.activity.alarmscreen

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.time.app.globalconstants.Constants.ACTION_DISABLE_ALARM
import com.example.time.app.globalconstants.Constants.ACTION_SNOOZE_ALARM
import com.example.time.presentation.common.theme.Theme
import com.example.time.app.globalconstants.Constants.EXTRA_ALARM_ID
import com.example.time.presentation.screens.alarmscreen.AlarmScreenDuringAlarm
import com.example.time.presentation.viewmodel.alarmscreen.AlarmUiEvent
import com.example.time.presentation.viewmodel.alarmscreen.AlarmViewModelDuringAlarm
import dagger.hilt.android.AndroidEntryPoint

@Suppress("DEPRECATION")
@AndroidEntryPoint
class AlarmActivity : ComponentActivity() {

    private val viewModel: AlarmViewModelDuringAlarm by viewModels()

    private val alarmActionReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val action = intent?.action
            Log.d("AlarmActivity", "Received local action: $action")

            when (action) {
                ACTION_DISABLE_ALARM -> viewModel.dismissToday()
                ACTION_SNOOZE_ALARM -> viewModel.snoozeToday()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleLockScreenFlags()
        val alarmId = intent.getIntExtra(EXTRA_ALARM_ID, -1)

        registerAlarmActions()
        setupUi(alarmId)
        collectUiEvents()
    }

    override fun onDestroy() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(alarmActionReceiver)
        super.onDestroy()
    }

    @SuppressLint("ObsoleteSdkInt")
    private fun handleLockScreenFlags() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
            setShowWhenLocked(true)
            setTurnScreenOn(true)
        } else {
            @Suppress("DEPRECATION")
            window.addFlags(
                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON or
                        WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON or
                        WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
            )
        }
    }

    private fun registerAlarmActions() {
        LocalBroadcastManager.getInstance(this).registerReceiver(
            alarmActionReceiver,
            IntentFilter().apply {
                addAction(ACTION_DISABLE_ALARM)
                addAction(ACTION_SNOOZE_ALARM)
            }
        )
    }

    private fun setupUi(alarmId: Int) {
        setContent {
            Theme(darkTheme = isSystemInDarkTheme(), dynamicColor = false) {
                Surface(modifier = Modifier.fillMaxSize()) {
                    AlarmScreenDuringAlarm(alarmId = alarmId, viewModel = viewModel)
                }
            }
        }
    }

    private fun collectUiEvents() {
        lifecycleScope.launchWhenStarted {
            viewModel.uiEvents.collect { event ->
                if (event is AlarmUiEvent.Finish) {
                    Log.d("AlarmActivity", "Finishing activity from SharedFlow")
                    finish()
                }
            }
        }
    }
}