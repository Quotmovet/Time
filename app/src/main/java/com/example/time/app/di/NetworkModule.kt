package com.example.time.app.di

import android.content.Context
import com.example.time.data.network.NetworkClient
import com.example.time.data.network.timescreen.TimeAPI
import com.example.time.data.network.timescreen.TimeApiNetworkClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://tools.aimylogic.com/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideTimeApi(retrofit: Retrofit): TimeAPI {
        return retrofit.create(TimeAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideNetworkClient(
        timeApi: TimeAPI,
        context: Context,
    ): NetworkClient {
        return TimeApiNetworkClient(timeApi, context)
    }
}
