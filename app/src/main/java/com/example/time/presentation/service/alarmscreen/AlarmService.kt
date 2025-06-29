package com.example.time.presentation.service.alarmscreen

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.example.time.R
import com.example.time.domain.contract.alarmscreen.AlarmScreenNotificationCreator
import com.example.time.domain.contract.alarmscreen.AlarmScreenSoundUriProvider
import com.example.time.domain.usecase.alarmscreen.PlayAlarmUseCase
import com.example.time.app.globalconstants.Constants.EXTRA_ALARM_ID
import com.example.time.app.globalconstants.Constants.EXTRA_ALARM_NAME
import com.example.time.app.globalconstants.Constants.EXTRA_SOUND_URI
import dagger.hilt.android.AndroidEntryPoint
import jakarta.inject.Inject

@AndroidEntryPoint
class AlarmService: Service() {

    @Inject lateinit var playAlarmUseCase: PlayAlarmUseCase
    @Inject lateinit var alarmScreenNotificationCreator: AlarmScreenNotificationCreator
    @Inject lateinit var soundUriProvider: AlarmScreenSoundUriProvider

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val alarmId = intent?.getIntExtra(EXTRA_ALARM_ID, -1) ?: -1
        val alarmName = intent?.getStringExtra(EXTRA_ALARM_NAME) ?: getString(R.string.alarm)
        val rawSoundUri = intent?.getStringExtra(EXTRA_SOUND_URI)
        val soundUri = soundUriProvider.getValidSoundUri(rawSoundUri)

        startForeground(1, alarmScreenNotificationCreator.create(alarmId, alarmName))
        playAlarmUseCase.execute(soundUri)

        return START_STICKY
    }

    override fun onDestroy() {
        playAlarmUseCase.stop()
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? = null
}
