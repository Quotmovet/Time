package com.example.time.app.di

import android.content.Context
import com.example.time.data.service.alarmscreen.AlarmScreenAlarmScreenAlarmPlayerService
import com.example.time.data.service.alarmscreen.AlarmScreenAlarmScreenNotificationService
import com.example.time.data.service.alarmscreen.AlarmScreenAlarmScreenVibratorManagerService
import com.example.time.data.service.alarmscreen.AlarmScreenSoundUriProviderService
import com.example.time.domain.contract.alarmscreen.AlarmScreenAlarmPlayer
import com.example.time.domain.contract.alarmscreen.AlarmScreenNotificationCreator
import com.example.time.domain.contract.alarmscreen.AlarmScreenSoundUriProvider
import com.example.time.domain.contract.alarmscreen.AlarmScreenVibratorManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    // AlarmScreen
    @Provides
    fun provideAlarmScreenAlarmScreenAlarmPlayerService(
        @ApplicationContext context: Context
    ): AlarmScreenAlarmPlayer = AlarmScreenAlarmScreenAlarmPlayerService(context)

    @Provides
    fun provideAlarmScreenAlarmScreenNotificationService(
        @ApplicationContext context: Context
    ): AlarmScreenNotificationCreator = AlarmScreenAlarmScreenNotificationService(context)

    @Provides
    fun provideAlarmScreenAlarmScreenVibratorManagerService(
        @ApplicationContext context: Context
    ): AlarmScreenVibratorManager = AlarmScreenAlarmScreenVibratorManagerService(context)

    @Provides
    fun provideAlarmScreenSoundUriProviderService(
        @ApplicationContext context: Context
    ): AlarmScreenSoundUriProvider = AlarmScreenSoundUriProviderService(context)

}