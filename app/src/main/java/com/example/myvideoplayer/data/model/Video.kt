package com.example.myvideoplayer.data.model


import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
@Entity
data class Video(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    val title: String,
    val videoUrl: String,
    var viewCount: Int = 0,
    var seekTime: Long? = null,
    var lastViewedTime: Long? = null,
    @Ignore var isSelected: Boolean = false
)
