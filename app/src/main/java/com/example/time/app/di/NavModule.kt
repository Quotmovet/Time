package com.example.time.app.di

import com.example.time.app.navigation.BottomNavItem
import com.example.time.app.navigation.Screens
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NavModule {

    @Provides
    @Singleton
    fun provideBottomNavItems(): List<BottomNavItem> {
        return BottomNavItem.items
    }

    @Provides
    @Singleton
    fun provideTopNavItems(): List<Screens> {
        return Screens.items
    }
}