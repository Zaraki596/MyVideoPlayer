package com.example.myvideoplayer.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myvideoplayer.data.local.VideoDao
import com.example.myvideoplayer.data.model.Video

@Database(
    entities = [Video::class],
    version = 1,
    exportSchema = false
)
abstract class VideoDatabase : RoomDatabase() {

    abstract fun getVideoDao() : VideoDao
}