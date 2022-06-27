package com.example.myvideoplayer.ui

import android.app.Application
import android.app.usage.UsageEvents
import android.os.Build
import androidx.lifecycle.*
import androidx.media3.common.Player
import com.example.myvideoplayer.data.VideoRepository
import com.example.myvideoplayer.data.model.Video
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VideoViewModel @Inject constructor(
    private val repository: VideoRepository
) : ViewModel() {

    private val _historyVideoData = MutableStateFlow<List<Video>>(listOf())
    val historyVideoData: StateFlow<List<Video>> get() = _historyVideoData

    private val _videoData = MutableStateFlow<List<Video>>(listOf())
    val videoData: StateFlow<List<Video>> get() = _videoData


    fun getAllVideos() {
        viewModelScope.launch {
            repository.getAllVideosList().collect {
                _videoData.emit(it)
            }
        }
    }

    fun getAllHistoryVideos() {
        viewModelScope.launch {
            repository.getHistoryVideos().collect {
                _historyVideoData.emit(it)
            }
        }
    }

    fun setVideoViews(video: Video) {
        viewModelScope.launch {
            repository.setVideoCount(video)
        }
    }

    fun setVideoTimeStamp(video: Video){
        viewModelScope.launch{
            repository.setVideoTimeStamp(video)
        }
    }


    fun setVideoLastViewedTime(video: Video){
        viewModelScope.launch{
            repository.setLastViewedTime(video)
        }
    }

}