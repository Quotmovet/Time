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
import java.io.FileNotFoundException

class AlarmScreenSoundUriProviderService @Inject constructor(
    @param:ApplicationContext private val context: Context
) : AlarmScreenSoundUriProvider {

    override fun getValidSoundUri(rawUri: String?): Uri {
        if (rawUri.isNullOrEmpty()) return getDefaultAlarmUri()

        return try {
            val uri = rawUri.toUri()
            if (isValidSoundUri(uri)) uri else getDefaultAlarmUri()
        } catch (e: IllegalArgumentException) {
            Log.w("SoundUriProvider", "Invalid URI format: $rawUri", e)
            getDefaultAlarmUri()
        } catch (e: SecurityException) {
            Log.w("SoundUriProvider", "No permission to access URI: $rawUri", e)
            getDefaultAlarmUri()
        }
    }

    private fun isValidSoundUri(uri: Uri): Boolean {
        return try {
            context.contentResolver.openInputStream(uri)?.use { true } ?: false
        } catch (e: FileNotFoundException) {
            Log.w("SoundUriProvider", "Sound file not found: $uri", e)
            false
        } catch (e: SecurityException) {
            Log.w("SoundUriProvider", "Access denied to URI: $uri", e)
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