package com.example.time.di

import com.example.time.data.repositories.timescreen.TimesRepositoryImpl
import com.example.time.domain.repositories.timescreen.TimesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object TimeScreenModel {
    @Provides
    fun provideTimeRepository(): TimesRepository = TimesRepositoryImpl()
}