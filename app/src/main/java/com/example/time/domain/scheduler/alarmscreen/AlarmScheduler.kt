package com.example.time.domain.scheduler.alarmscreen

import com.example.time.domain.model.alarmscreen.AlarmModel

interface AlarmScheduler {
    fun schedule(alarm: AlarmModel)
    fun cancel(alarm: AlarmModel)
    fun cancelForDay(alarm: AlarmModel, dayOfWeek: Int)
    fun schedulePostponeOnce(alarm: AlarmModel, triggerAtMillis: Long)
}