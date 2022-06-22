package com.example.myvideoplayer.di

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlin.jvm.internal.Intrinsics

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    fun providesMoshi(): Moshi = Moshi.Builder().build()
}