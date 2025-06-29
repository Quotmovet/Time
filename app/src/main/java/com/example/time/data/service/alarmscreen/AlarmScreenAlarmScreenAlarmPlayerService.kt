package com.example.time.data.service.alarmscreen

import android.content.Context
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import com.example.time.domain.contract.alarmscreen.AlarmScreenAlarmPlayer
import dagger.hilt.android.qualifiers.ApplicationContext
import jakarta.inject.Inject

class AlarmScreenAlarmScreenAlarmPlayerService @Inject constructor(
    @param:ApplicationContext private val context: Context
): AlarmScreenAlarmPlayer {
    private var mediaPlayer: MediaPlayer? = null

    override fun play(uri: Uri) {
        stop()
        mediaPlayer = MediaPlayer().apply {
            setDataSource(context, uri)
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_ALARM)
                    .build()
            )
            isLooping = true
            prepare()
            start()
        }
    }

    override fun stop() {
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}