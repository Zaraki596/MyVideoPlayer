package com.example.myvideoplayer.di

import android.content.Context
import com.example.myvideoplayer.data.VideoRepository
import com.example.myvideoplayer.data.local.VideoDao
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

    @Provides
    @ViewModelScoped
    fun providesRepo(videoDao: VideoDao, moshi: Moshi, context: Context): VideoRepository =
        VideoRepository(videoDao, moshi, context)
}