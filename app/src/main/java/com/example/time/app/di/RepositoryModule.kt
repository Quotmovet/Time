package com.example.time.app.di

import com.example.time.data.datasource.timescreen.TimeZoneCsvDataSource
import com.example.time.data.db.AppDatabase
import com.example.time.data.network.timescreen.TimeApiNetworkClient
import com.example.time.data.repositories.alarmscreen.AlarmScreenRepositoryImpl
import com.example.time.data.repositories.timescreen.CurrentTimeRepositoryImpl
import com.example.time.data.repositories.timescreen.DataSourceTimeRepositoryImpl
import com.example.time.data.repositories.timescreen.NetworkTimeDataRepositoryImpl
import com.example.time.data.repositories.timescreen.SelectedTimeZoneRepositoryImpl
import com.example.time.domain.repositories.alarmscreen.AlarmScreenRepository
import com.example.time.domain.repositories.timescreen.CurrentTimeRepository
import com.example.time.domain.repositories.timescreen.DataSourceTimeRepository
import com.example.time.domain.repositories.timescreen.SelectedTimeZoneRepository
import com.example.time.domain.repositories.timescreen.TimeDataRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    // TimeScreen
    @Provides
    fun provideCurrentTimeRepository(): CurrentTimeRepository = CurrentTimeRepositoryImpl()

    @Provides
    @Singleton
    fun provideTimeZoneRepository(timeApiNetworkClient: TimeApiNetworkClient): TimeDataRepository {
        return NetworkTimeDataRepositoryImpl(timeApiNetworkClient)
    }

    @Provides
    @Singleton
    fun provideSelectedTimeZoneRepository(appDatabase: AppDatabase): SelectedTimeZoneRepository {
        return SelectedTimeZoneRepositoryImpl(appDatabase)
    }

    @Provides
    @Singleton
    fun provideDataSourceTimeRepository(timeZoneCsvDataSource: TimeZoneCsvDataSource): DataSourceTimeRepository {
        return DataSourceTimeRepositoryImpl(timeZoneCsvDataSource)
    }

    // AlarmScreen
    @Provides
    @Singleton
    fun provideAlarmRepository(appDatabase: AppDatabase): AlarmScreenRepository {
        return AlarmScreenRepositoryImpl(appDatabase)
    }
}
