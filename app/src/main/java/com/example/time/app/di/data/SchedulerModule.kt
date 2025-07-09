package com.example.time.app.di.data

import android.content.Context
import com.example.time.data.scheduler.alarmscreen.AlarmScreenScreenScheduler
import com.example.time.domain.scheduler.alarmscreen.AlarmScreenScheduler
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
        @ApplicationContext context: Context,
    ): AlarmScreenScheduler {
        return AlarmScreenScreenScheduler(context)
    }
}
