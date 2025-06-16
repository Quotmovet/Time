package com.example.time.data.scheduler.alarmscreen

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import com.example.time.data.receiver.alarmscreen.AlarmReceiver
import com.example.time.domain.model.alarmscreen.AlarmModel
import com.example.time.domain.scheduler.alarmscreen.AlarmScheduler
import java.util.Calendar
import javax.inject.Inject

class AndroidAlarmScheduler @Inject constructor (private val context: Context): AlarmScheduler {

    override fun schedule(alarm: AlarmModel) {

        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            if (!alarmManager.canScheduleExactAlarms()) {
                // Здесь должна быть логика показа диалога и перехода в настройки
                // Например, можно отправить Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM)
                Log.w("AlarmScheduler", "Exact alarm permission not granted")

                // Лучше выйти, чтобы не упасть или не ставить "неточный" будильник
                return
            }
        }

        val intent = Intent(context, AlarmReceiver::class.java).apply {
            putExtra("EXTRA_MESSAGE", alarm.id)
            putExtra("EXTRA_SOUND_URI", alarm.sound)
        }

        val pendingIntent = PendingIntent.getBroadcast(
            context,
            alarm.id,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val calendar = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, alarm.hour)
            set(Calendar.MINUTE, alarm.minute)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)

            // Если время уже прошло — ставим на следующий день
            if (before(Calendar.getInstance())) {
                add(Calendar.DAY_OF_YEAR, 1)
            }
        }

        try {
            alarmManager.setExactAndAllowWhileIdle(
                AlarmManager.RTC_WAKEUP,
                calendar.timeInMillis,
                pendingIntent
            )
            Log.d("AlarmScheduler", "Alarm scheduled for: ${calendar.time}")
        } catch (e: SecurityException) {
            Log.e("AlarmScheduler", "Permission denied to schedule exact alarms", e)
        }
    }

    override fun cancel(alarm: AlarmModel) {
        val intent = Intent(context, AlarmReceiver::class.java)

        val pendingIntent = PendingIntent.getBroadcast(
            context,
            alarm.id,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.cancel(pendingIntent)
        Log.d("AlarmScheduler", "Alarm with id ${alarm.id} cancelled")
    }

}