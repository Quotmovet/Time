package com.example.time.app.di.domain

import com.example.time.domain.usecase.stopwatchscreen.StopwatchUseCase
import com.example.time.domain.usecase.stopwatchscreen.impl.StopwatchUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideStopwatchUseCase(): StopwatchUseCase = StopwatchUseCaseImpl()
}