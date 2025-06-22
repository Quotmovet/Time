package com.example.time.data.scheduler.alarmscreen

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.provider.Settings
import android.os.Build
import android.util.Log
import com.example.time.data.receiver.alarmscreen.AlarmReceiver
import com.example.time.domain.model.alarmscreen.AlarmModel
import com.example.time.domain.scheduler.alarmscreen.AlarmScheduler
import java.util.Calendar
import javax.inject.Inject
import androidx.core.net.toUri
import java.util.Date

class AndroidAlarmScheduler @Inject constructor (private val context: Context): AlarmScheduler {

    override fun schedule(alarm: AlarmModel) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S && !alarmManager.canScheduleExactAlarms()) {
            Log.w("AlarmScheduler", "Exact alarm permission not granted")
            val intent = Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM).apply {
                data = "package:${context.packageName}".toUri()
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            context.startActivity(intent)
            return
        }

        val daysOfWeek = alarm.days.split(",").mapNotNull { it.trim().toIntOrNull() }

        for (day in daysOfWeek) {
            val calendar = Calendar.getInstance().apply {
                timeInMillis = System.currentTimeMillis()
                set(Calendar.HOUR_OF_DAY, alarm.hour)
                set(Calendar.MINUTE, alarm.minute)
                set(Calendar.SECOND, 0)
                set(Calendar.MILLISECOND, 0)

                set(Calendar.DAY_OF_WEEK, convertToCalendarDay(day))

                if (before(Calendar.getInstance())) {
                    add(Calendar.WEEK_OF_YEAR, 1)
                }
            }

            val intent = Intent(context, AlarmReceiver::class.java).apply {
                putExtra("EXTRA_ALARM_ID", alarm.id)
                putExtra("EXTRA_SOUND_URI", alarm.sound)
            }

            val pendingIntent = PendingIntent.getBroadcast(
                context,
                alarm.id * 10 + day, // уникальный requestCode для каждого дня
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )

            try {
                alarmManager.setExactAndAllowWhileIdle(
                    AlarmManager.RTC_WAKEUP,
                    calendar.timeInMillis,
                    pendingIntent
                )
                Log.d("AlarmScheduler", "Scheduled alarm ${alarm.id} for day $day at ${calendar.time}")
            } catch (e: SecurityException) {
                Log.e("AlarmScheduler", "Permission denied to schedule exact alarm", e)
            }
        }
    }

    override fun cancel(alarm: AlarmModel) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val daysOfWeek = alarm.days.split(",").mapNotNull { it.trim().toIntOrNull() }

        for (day in daysOfWeek) {
            val intent = Intent(context, AlarmReceiver::class.java)

            val pendingIntent = PendingIntent.getBroadcast(
                context,
                alarm.id * 10 + day,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )

            alarmManager.cancel(pendingIntent)
            Log.d("AlarmScheduler", "Cancelled alarm ${alarm.id} for day $day")
        }
    }

    override fun cancelForDay(alarm: AlarmModel, dayOfWeek: Int) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmReceiver::class.java)

        val pendingIntent = PendingIntent.getBroadcast(
            context,
            alarm.id * 10 + dayOfWeek,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        alarmManager.cancel(pendingIntent)
        Log.d("AlarmScheduler", "Cancelled alarm ${alarm.id} only for day $dayOfWeek")
    }

    override fun schedulePostponeOnce(alarm: AlarmModel, triggerAtMillis: Long) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val intent = Intent(context, AlarmReceiver::class.java).apply {
            putExtra("EXTRA_ALARM_ID", alarm.id)
            putExtra("EXTRA_SOUND_URI", alarm.sound)
        }

        val requestCode = alarm.id * 1000 + 1 // уникальный временный ID
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            requestCode,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            triggerAtMillis,
            pendingIntent
        )

        Log.d("AlarmScheduler", "Scheduled one-time alarm ${alarm.id} at ${Date(triggerAtMillis)}")
    }

    private fun convertToCalendarDay(day: Int): Int {
        return when (day) {
            0 -> Calendar.MONDAY
            1 -> Calendar.TUESDAY
            2 -> Calendar.WEDNESDAY
            3 -> Calendar.THURSDAY
            4 -> Calendar.FRIDAY
            5 -> Calendar.SATURDAY
            6 -> Calendar.SUNDAY
            else -> Calendar.MONDAY // fallback
        }
    }
}