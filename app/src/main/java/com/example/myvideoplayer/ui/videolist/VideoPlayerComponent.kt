package com.example.myvideoplayer.ui.videolist

import android.content.Context
import androidx.lifecycle.*
import androidx.media3.common.MediaItem
import androidx.media3.common.util.Util
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView

class VideoPlayerComponent(context: Context, playerView: PlayerView, mediaItems: List<MediaItem>) :
    DefaultLifecycleObserver {

    private var context: Context
    private var mediaItems: List<MediaItem>
    private var playerView: PlayerView
    private var player: ExoPlayer? = null

    private var playWhenReady = true
    private var currentItem = 0
    private var playbackPosition = 0L

    init {
        this.context = context
        this.mediaItems = mediaItems
        this.playerView = playerView
    }


    private fun initializePlayer() {
        player = ExoPlayer.Builder(context)
            .build()
            .also { exoPlayer ->
                exoPlayer.addMediaItems(mediaItems)
                exoPlayer.playWhenReady = playWhenReady
                exoPlayer.seekTo(currentItem, playbackPosition)
                exoPlayer.prepare()
                exoPlayer.play()
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

    override fun onStart(owner: LifecycleOwner) {
        if (Util.SDK_INT > 23) initializePlayer()
    }

    override fun onResume(owner: LifecycleOwner) {
        if (Util.SDK_INT > 23) {
            releasePlayer()
        }
    }

    override fun onPause(owner: LifecycleOwner) {
        if (Util.SDK_INT <= 23) {
            releasePlayer()
        }
    }

    override fun onStop(owner: LifecycleOwner) {
        if (Util.SDK_INT > 23) {
            releasePlayer()
        }
    }


}