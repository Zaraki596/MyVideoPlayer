package com.example.myvideoplayer.data.model


import android.icu.text.CaseMap
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
@Entity
data class Video(
    @PrimaryKey
    val id: String,
    val title: String,
    @Json(name = "uri") val videoUrl: String,
    var viewCount: Int = 0,
    var seekTime: Long? = null,
    var lastViewedTime: Long? = null,
) {
    @Ignore
    var isSelected: Boolean = false
}
