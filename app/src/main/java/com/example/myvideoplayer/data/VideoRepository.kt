package com.example.myvideoplayer.data

import android.content.Context
import android.util.Log
import com.example.myvideoplayer.data.local.VideoDao
import com.example.myvideoplayer.data.model.Video
import com.example.myvideoplayer.utils.readFile
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class VideoRepository @Inject constructor(
    private val videoDb: VideoDatabase,
    private val moshi: Moshi,
    private val context: Context
) {

    private val videoDao = videoDb.getVideoDao()

    fun getAllVideosList(): Flow<List<Video>> {
        val listType = Types.newParameterizedType(List::class.java, Video::class.java)
        val adapter: JsonAdapter<List<Video>> = moshi.adapter(listType)

        val jsonString = context.assets.readFile("data.json")

        return flowOf(adapter.fromJson(jsonString) ?: listOf())
    }


    fun getHistoryVideos(): Flow<List<Video>> = videoDao.getHistoryVideos()

    suspend fun setVideoCount(video: Video) = videoDao.setViewCount(video.viewCount, video.id)

    suspend fun setVideoTimeStamp(video: Video) = videoDao.setSeekTime(video.seekTime, video.id)

    suspend fun setLastViewedTime(video: Video) =
        videoDao.setLastViewedTime(video.lastViewedTime, video.id)

}