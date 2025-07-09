package com.example.time.domain.usecase.alarmscreen

import android.net.Uri

interface AlarmScreenAlarmPlayer {
    fun play(uri: Uri)

    fun stop()
}
