package com.example.time.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.time.data.db.alarmscreen.dao.AlarmDao
import com.example.time.data.db.alarmscreen.entity.AlarmEntity
import com.example.time.data.db.timescreen.dao.TimeDataDao
import com.example.time.data.db.timescreen.entity.TimeDataEntity

@Database(
    version = 4,
    entities = [TimeDataEntity::class, AlarmEntity::class],
    exportSchema = false,
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getTimeDataDao(): TimeDataDao

    abstract fun getAlarmDao(): AlarmDao
}
