package com.example.time.app.di

import android.content.Context
import androidx.room.Room
import com.example.time.data.db.AppDatabase
import com.example.time.data.db.alarmscreen.dao.AlarmDao
import com.example.time.data.db.timescreen.dao.TimeDataDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "time-database"
        )
            .fallbackToDestructiveMigration(false)
            .build()
    }

    // TimeScreen
    @Provides
    fun provideTimeDataDao(database: AppDatabase): TimeDataDao {
        return database.getTimeDataDao()
    }

    // AlarmScreen
    @Provides
    fun provideAlarmDao(database: AppDatabase): AlarmDao {
        return database.getAlarmDao()
    }

}