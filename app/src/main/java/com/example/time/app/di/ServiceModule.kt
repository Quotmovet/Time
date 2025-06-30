package com.example.time.app.di

import android.content.Context
import com.example.time.data.service.alarmscreen.AlarmScreenAlarmPlayerService
import com.example.time.data.service.alarmscreen.AlarmScreenVibratorManagerService
import com.example.time.data.service.alarmscreen.AlarmScreenNotificationService
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
    ): AlarmScreenAlarmPlayer = AlarmScreenAlarmPlayerService(context)

    @Provides
    fun provideAlarmScreenAlarmScreenNotificationService(
        @ApplicationContext context: Context
    ): AlarmScreenNotificationCreator = AlarmScreenNotificationService(context)

    @Provides
    fun provideAlarmScreenAlarmScreenVibratorManagerService(
        @ApplicationContext context: Context
    ): AlarmScreenVibratorManager = AlarmScreenVibratorManagerService(context)

    @Provides
    fun provideAlarmScreenSoundUriProviderService(
        @ApplicationContext context: Context
    ): AlarmScreenSoundUriProvider = AlarmScreenSoundUriProviderService(context)

}