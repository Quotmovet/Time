package com.example.time.domain.usecase.alarmscreen

import android.net.Uri

interface AlarmScreenSoundUriProvider {
    fun getValidSoundUri(rawUri: String?): Uri
}
