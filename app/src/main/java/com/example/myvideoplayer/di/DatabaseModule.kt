package com.example.myvideoplayer.di

import android.content.Context
import android.provider.DocumentsContract
import androidx.room.Room
import com.example.myvideoplayer.data.VideoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun providesDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(app, VideoDatabase::class.java, "my_video_db")


    @Singleton
    @Provides
    fun providesVideoDao(db: VideoDatabase) = db.getVideoDao()

}