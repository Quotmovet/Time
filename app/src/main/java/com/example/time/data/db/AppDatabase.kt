package com.example.time.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.time.data.db.timescreen.dao.TimeDataDao
import com.example.time.data.db.timescreen.entity.TimeDataEntity

@Database(version = 2, entities = [TimeDataEntity::class], exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getTimeDataDao(): TimeDataDao
}
