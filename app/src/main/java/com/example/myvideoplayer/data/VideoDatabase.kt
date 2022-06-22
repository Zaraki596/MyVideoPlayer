package com.example.myvideoplayer.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myvideoplayer.data.local.VideoDao
import com.example.myvideoplayer.data.model.Video
import dagger.hilt.android.qualifiers.ApplicationContext
import it.czerwinski.android.hilt.annotations.FactoryMethod
import javax.inject.Singleton

@Database(
    entities = [Video::class],
    version = 1,
    exportSchema = false
)

abstract class VideoDatabase : RoomDatabase() {

    @FactoryMethod
    @Singleton
    abstract fun getVideoDao(): VideoDao


    object Factory {
        @FactoryMethod
        @Singleton
        fun create(
            @ApplicationContext context: Context
        ): VideoDatabase =
            Room.databaseBuilder(context, VideoDatabase::class.java, "my_video_db")
                .build()
    }
}