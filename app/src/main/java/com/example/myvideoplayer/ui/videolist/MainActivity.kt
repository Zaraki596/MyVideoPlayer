package com.example.myvideoplayer.ui.videolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.media3.common.MediaItem
import androidx.media3.common.util.Util
import androidx.media3.exoplayer.ExoPlayer
import com.example.myvideoplayer.data.model.Video
import com.example.myvideoplayer.databinding.ActivityMainBinding
import com.example.myvideoplayer.ui.VideoViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: VideoViewModel by viewModels()

    private val viewBinding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private var player: ExoPlayer? = null

    private var playWhenReady = false
    private var currentItem = 0
    private var playbackPosition = 0L
    private val mediaList = mutableListOf<MediaItem>()
    private val playlistAdapter by lazy { PlaylistAdapter(::onVideoClicked) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)
        fetchVideos()
        viewBinding.rvPlayerList.adapter = playlistAdapter
    }

    private fun fetchVideos() {
        viewModel.getAllVideos()
        lifecycleScope.launch {
            viewModel.videoData.collect() { videosList ->
                for (video in videosList) {
                    mediaList.add(
                        MediaItem.Builder()
                            .setUri(video.videoUrl)
                            .setMediaId(video.id)
                            .build()
                    )
                }
                playlistAdapter.submitList(videosList)
            }
        }
    }

    private fun initializePlayer() {
        player = ExoPlayer.Builder(this)
            .build()
            .also { exoPlayer ->
                viewBinding.playerView.player = exoPlayer
                exoPlayer.setMediaItems(mediaList)
                exoPlayer.playWhenReady = playWhenReady
                exoPlayer.seekTo(currentItem, playbackPosition)
                exoPlayer.prepare()
            }
    }

    private fun releasePlayer() {
        player?.let { exoPlayer ->
            playbackPosition = exoPlayer.currentPosition
            currentItem = exoPlayer.currentMediaItemIndex
            playWhenReady = exoPlayer.playWhenReady
            exoPlayer.release()
        }
        player = null
    }

    private fun onVideoClicked(video: Video, position: Int) {
        player?.apply {
            setMediaItem(mediaList[position])
            prepare()
        }
    }

    public override fun onStart() {
        super.onStart()
        if (Util.SDK_INT > 23) {
            initializePlayer()
        }
    }

    public override fun onResume() {
        super.onResume()
        if (Util.SDK_INT <= 23 || player == null) {
            initializePlayer()
        }
    }

    public override fun onPause() {
        super.onPause()
        if (Util.SDK_INT <= 23) {
            releasePlayer()
        }
    }

    public override fun onStop() {
        super.onStop()
        if (Util.SDK_INT > 23) {
            releasePlayer()
        }
    }

}