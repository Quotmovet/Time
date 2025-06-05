package com.example.time.di

import com.example.time.domain.interactor.timescreen.SelectedTimeZoneInteractor
import com.example.time.domain.interactor.timescreen.TimeZoneDataInteractor
import com.example.time.domain.interactor.timescreen.impl.SelectedTimeZoneInteractorImpl
import com.example.time.domain.interactor.timescreen.impl.TimeZoneDataInteractorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DomainModule {

    @Binds
    abstract fun bindTimeZoneInteractor(
        timeZoneDataInteractor: TimeZoneDataInteractorImpl
    ): TimeZoneDataInteractor

    @Binds
    abstract fun bindSelectedTimeZoneInteractor(
        selectedTimeZoneInteractor: SelectedTimeZoneInteractorImpl
    ): SelectedTimeZoneInteractor

}