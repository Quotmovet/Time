package com.example.time.di

import com.example.time.data.db.AppDatabase
import com.example.time.data.network.timescreen.TimeApiNetworkClient
import com.example.time.data.repositories.timescreen.CurrentTimeRepositoryImpl
import com.example.time.data.repositories.timescreen.SelectedTimeZoneRepositoryImpl
import com.example.time.data.repositories.timescreen.TimeDataRepositoryImpl
import com.example.time.domain.repositories.timescreen.CurrentTimeRepository
import com.example.time.domain.repositories.timescreen.SelectedTimeZoneRepository
import com.example.time.domain.repositories.timescreen.TimeDataRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModel {

    @Provides
    fun provideCurrentTimeRepository(): CurrentTimeRepository = CurrentTimeRepositoryImpl()

    @Provides
    @Singleton
    fun provideTimeZoneRepository(
        timeApiNetworkClient: TimeApiNetworkClient,
    ): TimeDataRepository {
        return TimeDataRepositoryImpl(timeApiNetworkClient)
    }

    @Provides
    @Singleton
    fun provideSelectedTimeZoneRepository(
        appDatabase: AppDatabase
    ): SelectedTimeZoneRepository {
        return SelectedTimeZoneRepositoryImpl(appDatabase)
    }

}