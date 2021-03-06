package com.example.myvideoplayer.di

import android.content.Context
import com.example.myvideoplayer.data.VideoDatabase
import com.example.myvideoplayer.data.VideoRepository
import com.example.myvideoplayer.data.local.VideoDao
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped


@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

    @Provides
    @ViewModelScoped
    fun providesRepo(videoDatabase: VideoDatabase, moshi: Moshi, @ApplicationContext context: Context): VideoRepository =
        VideoRepository(videoDatabase, moshi, context)
}