package com.example.time.presentation.activity.alarmscreen

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.Surface
import com.example.time.presentation.common.theme.Theme
import com.example.time.presentation.screens.alarmscreen.AlarmScreenDuringAlarm
import dagger.hilt.android.AndroidEntryPoint

@Suppress("DEPRECATION")
@AndroidEntryPoint
class AlarmActivity : ComponentActivity() {
    @SuppressLint("ObsoleteSdkInt")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
            setShowWhenLocked(true)
            setTurnScreenOn(true)
        } else {
            window.addFlags(
                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON or
                WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON or
                WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
            )
        }

        val alarmId = intent.getIntExtra("EXTRA_ALARM_ID", -1)

        setContent {
            Theme(
                darkTheme = isSystemInDarkTheme(),
                dynamicColor = false
            ) {
                Surface {
                    AlarmScreenDuringAlarm(alarmId = alarmId)
                }
            }
        }
    }
}