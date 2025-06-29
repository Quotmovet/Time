package com.example.time.domain.contract.alarmscreen

import android.net.Uri

interface AlarmScreenSoundUriProvider {
    fun getValidSoundUri(rawUri: String?): Uri
}