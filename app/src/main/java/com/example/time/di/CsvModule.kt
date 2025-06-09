package com.example.time.di

import android.content.Context
import com.example.time.data.datasource.timescreen.TimeZoneCsvDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CsvModule {

    @Provides
    @Singleton
    fun provideTimeZoneCsvDataSource(
        @ApplicationContext context: Context
    ): TimeZoneCsvDataSource {
        return TimeZoneCsvDataSource(context)
    }
}