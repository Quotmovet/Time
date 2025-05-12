package com.example.time.di

import com.example.time.navigation.BottomNavItem
import com.example.time.navigation.TopNavItem
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideBottomNavItems(): List<BottomNavItem> {
        return BottomNavItem.items
    }

    @Provides
    @Singleton
    fun provideTopNavItems(): List<TopNavItem.SettingsScreen> {
        return TopNavItem.items
    }
}