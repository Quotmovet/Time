package com.example.time.data.scheduler.alarmscreen

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Settings
import android.util.Log
import androidx.core.net.toUri
import com.example.time.app.globalconstants.Constants.EXTRA_ALARM_DATE
import com.example.time.app.globalconstants.Constants.EXTRA_ALARM_ID
import com.example.time.app.globalconstants.Constants.EXTRA_ALARM_NAME
import com.example.time.app.globalconstants.Constants.EXTRA_SOUND_URI
import com.example.time.app.receiver.alarmscreen.AlarmReceiver
import com.example.time.domain.model.alarmscreen.AlarmModel
import com.example.time.domain.scheduler.alarmscreen.AlarmScreenScheduler
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class AlarmScreenScreenScheduler
    @Inject
    constructor(
        private val context: Context,
    ) : AlarmScreenScheduler {
        companion object {
            private const val REQUEST_CODE_OFFSET_PER_DAY = 10
            private const val REQUEST_CODE_BLOCK_SIZE = 1000
            private const val REQUEST_CODE_TYPE_ALARM_TRIGGER = 1
        }

        private val date = SimpleDateFormat("EEE, dd MMM", Locale.getDefault()).format(Date())

        override fun schedule(alarm: AlarmModel) {
            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S && !alarmManager.canScheduleExactAlarms()) {
                Log.w("AlarmScheduler", "Exact alarm permission not granted")
                val intent =
                    Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM).apply {
                        data = "package:${context.packageName}".toUri()
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    }
                context.startActivity(intent)
                return
            }

            val daysOfWeek = alarm.days.split(",").mapNotNull { it.trim().toIntOrNull() }

            for (day in daysOfWeek) {
                val calendar =
                    Calendar.getInstance().apply {
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

                val intent =
                    Intent(context, AlarmReceiver::class.java).apply {
                        putExtra(EXTRA_ALARM_ID, alarm.id)
                        putExtra(EXTRA_SOUND_URI, alarm.sound)
                        putExtra(EXTRA_ALARM_NAME, alarm.name)
                        putExtra(EXTRA_ALARM_DATE, date)
                    }

                val pendingIntent =
                    PendingIntent.getBroadcast(
                        context,
                        alarm.id * REQUEST_CODE_OFFSET_PER_DAY + day, // unique requestCode for each alarmID
                        intent,
                        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE,
                    )

                try {
                    alarmManager.setExactAndAllowWhileIdle(
                        AlarmManager.RTC_WAKEUP,
                        calendar.timeInMillis,
                        pendingIntent,
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

                val pendingIntent =
                    PendingIntent.getBroadcast(
                        context,
                        alarm.id * REQUEST_CODE_OFFSET_PER_DAY + day,
                        intent,
                        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE,
                    )

                alarmManager.cancel(pendingIntent)
                Log.d("AlarmScheduler", "Cancelled alarm ${alarm.id} for day $day")
            }
        }

        override fun cancelForDay(
            alarm: AlarmModel,
            dayOfWeek: Int,
        ) {
            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val intent = Intent(context, AlarmReceiver::class.java)

            val pendingIntent =
                PendingIntent.getBroadcast(
                    context,
                    alarm.id * REQUEST_CODE_OFFSET_PER_DAY + dayOfWeek,
                    intent,
                    PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE,
                )

            alarmManager.cancel(pendingIntent)
            Log.d("AlarmScheduler", "Cancelled alarm ${alarm.id} only for day $dayOfWeek")
        }

        override fun schedulePostponeOnce(
            alarm: AlarmModel,
            triggerAtMillis: Long,
        ) {
            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

            val intent =
                Intent(context, AlarmReceiver::class.java).apply {
                    putExtra(EXTRA_ALARM_ID, alarm.id)
                    putExtra(EXTRA_SOUND_URI, alarm.sound)
                    putExtra(EXTRA_ALARM_NAME, alarm.name)
                    putExtra(EXTRA_ALARM_DATE, date)
                }

            val requestCode = alarm.id * REQUEST_CODE_BLOCK_SIZE + REQUEST_CODE_TYPE_ALARM_TRIGGER // unique requestCode for each alarmID
            val pendingIntent =
                PendingIntent.getBroadcast(
                    context,
                    requestCode,
                    intent,
                    PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE,
                )

            alarmManager.setExactAndAllowWhileIdle(
                AlarmManager.RTC_WAKEUP,
                triggerAtMillis,
                pendingIntent,
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
