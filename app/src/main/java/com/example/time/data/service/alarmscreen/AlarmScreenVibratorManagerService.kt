package com.example.time.data.service.alarmscreen

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import com.example.time.domain.contract.alarmscreen.AlarmScreenVibratorManager
import dagger.hilt.android.qualifiers.ApplicationContext
import jakarta.inject.Inject

@Suppress("DEPRECATION")
class AlarmScreenVibratorManagerService@Inject constructor(
    @param:ApplicationContext private val context: Context
): AlarmScreenVibratorManager {
    private val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

    @SuppressLint("ObsoleteSdkInt")
    override fun start() {
        val pattern = longArrayOf(0, 500, 300, 500, 300, 1000)
        val effect = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            VibrationEffect.createWaveform(pattern, 1)
        } else {
            return vibrator.vibrate(pattern, 0)
        }
        vibrator.vibrate(effect)
    }

    override fun stop() {
        vibrator.cancel()
    }
}