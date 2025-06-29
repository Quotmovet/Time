package com.example.time.app.di

import android.content.Context
import com.example.time.data.scheduler.alarmscreen.AlarmScreenScreenScheduler
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
    ): com.example.time.domain.scheduler.alarmscreen.AlarmScreenScheduler {
        return AlarmScreenScreenScheduler(context)
    }

}