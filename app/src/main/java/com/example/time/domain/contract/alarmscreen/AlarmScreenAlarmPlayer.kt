package com.example.time.domain.contract.alarmscreen

import android.net.Uri

interface AlarmScreenAlarmPlayer {
    fun play(uri: Uri)
    fun stop()
}