package com.example.time.di

import android.content.Context
import com.example.time.data.scheduler.alarmscreen.AndroidAlarmScheduler
import com.example.time.domain.scheduler.alarmscreen.AlarmScheduler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SchedulerModule {

    @Provides
    @Singleton
    fun provideAlarmScheduler(
        @ApplicationContext context: Context
    ): AlarmScheduler {
        return AndroidAlarmScheduler(context)
    }

}