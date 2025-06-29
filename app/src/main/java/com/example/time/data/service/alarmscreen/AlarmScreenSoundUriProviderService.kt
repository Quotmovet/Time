package com.example.time.data.service.alarmscreen

import android.content.Context
import android.media.RingtoneManager
import android.net.Uri
import android.util.Log
import androidx.core.net.toUri
import com.example.time.R
import com.example.time.domain.contract.alarmscreen.AlarmScreenSoundUriProvider
import dagger.hilt.android.qualifiers.ApplicationContext
import jakarta.inject.Inject

class AlarmScreenSoundUriProviderService@Inject constructor(
    @param:ApplicationContext private val context: Context
): AlarmScreenSoundUriProvider {

    override fun getValidSoundUri(rawUri: String?): Uri {
        if (rawUri.isNullOrEmpty()) return getDefaultAlarmUri()

        return try {
            val uri = rawUri.toUri()
            if (isValidSoundUri(uri)) uri else getDefaultAlarmUri()
        } catch (e: Exception) {
            Log.w("SoundUriProvider", "Invalid sound URI: $rawUri", e)
            getDefaultAlarmUri()
        }
    }

    private fun isValidSoundUri(uri: Uri): Boolean {
        return try {
            context.contentResolver.openInputStream(uri)?.use { true } ?: false
        } catch (e: Exception) {
            Log.w("SoundUriProvider", "Cannot access sound URI: $uri", e)
            false
        }
    }

    private fun getDefaultAlarmUri(): Uri {
        return RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
            ?: RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            ?: RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE)
            ?: "android.resource://${context.packageName}/${R.raw.default_alarm}".toUri()
    }
}