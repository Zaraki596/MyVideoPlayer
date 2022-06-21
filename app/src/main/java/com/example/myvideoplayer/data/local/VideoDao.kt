package com.example.myvideoplayer.data.local

import androidx.room.*
import com.example.myvideoplayer.data.model.Video
import kotlinx.coroutines.flow.Flow

@Dao
interface VideoDao {

    @Query("SELECT * FROM Video")
    fun getAllVideos(): Flow<List<Video>>

    @Query("Select * from Video where viewCount > 0")
    fun getHistoryVideos(): Flow<List<Video>>

    @Insert
    suspend fun insertVideo(video: Video)

    @Query("Update Video SET viewCount = :viewCount where id = :id")
    suspend fun setViewCount(viewCount: Int, id: Int) : Int

    @Query("Update Video SET seekTime = :seekTime where id = :id")
    suspend fun setSeekTime(seekTime: Long?, id: Int) : Int

    @Query("Update Video SET lastViewedTime = :lastViewedTime where id = :id")
    suspend fun setLastViewedTime(lastViewedTime: Long?, id: Int) : Int

    @Delete
    suspend fun deleteVideo(video: Video)
}