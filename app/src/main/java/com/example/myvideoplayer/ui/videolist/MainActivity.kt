package com.example.myvideoplayer.ui.videolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.media3.common.MediaItem
import com.example.myvideoplayer.databinding.ActivityMainBinding
import com.example.myvideoplayer.ui.VideoViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel : VideoViewModel by viewModels()

    private val viewBinding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var videoPlayerComponent: VideoPlayerComponent


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)
        videoPlayerComponent = VideoPlayerComponent(this, viewBinding.playerView, getMediaItems())

    }

    private fun getMediaItems(): List<MediaItem> {
        val mediaList = mutableListOf<MediaItem>()
        viewModel.getAllVideos()
        CoroutineScope(Dispatchers.Main).launch {
            viewModel.videoData.onEach { videosList ->
                for (video in videosList) {
                    mediaList.add(MediaItem.fromUri(video.videoUrl))
                }
            }
        }
        return mediaList.toList()
    }

}