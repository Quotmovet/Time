package com.example.time.domain.usecase.alarmscreen

import android.net.Uri
import com.example.time.domain.contract.alarmscreen.AlarmScreenAlarmPlayer
import com.example.time.domain.contract.alarmscreen.AlarmScreenVibratorManager
import jakarta.inject.Inject

class PlayAlarmUseCase
    @Inject
    constructor(
        private val alarmScreenAlarmPlayer: AlarmScreenAlarmPlayer,
        private val alarmScreenVibratorManager: AlarmScreenVibratorManager,
    ) {
        fun execute(uri: Uri) {
            alarmScreenVibratorManager.start()
            alarmScreenAlarmPlayer.play(uri)
        }

        fun stop() {
            alarmScreenVibratorManager.stop()
            alarmScreenAlarmPlayer.stop()
        }
    }
