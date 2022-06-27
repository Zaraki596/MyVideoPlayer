package com.example.myvideoplayer.ui.videolist

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.View.OnClickListener
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.myvideoplayer.R
import com.example.myvideoplayer.data.model.Video
import com.example.myvideoplayer.databinding.ItemVideoBinding

class PlaylistAdapter(private val onItemClicked: (Video, position: Int) -> Unit) :
    ListAdapter<Video, PlaylistAdapter.PlaylistViewHolder>(VideoDC) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PlaylistViewHolder(

        ItemVideoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: PlaylistViewHolder, position: Int) =
        holder.bind(getItem(position), position, onItemClicked)

    fun swapData(data: List<Video>) {
        submitList(data.toMutableList())
    }

    inner class PlaylistViewHolder(
        private val binding: ItemVideoBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Video, position: Int, onItemClicked: (Video, position: Int) -> Unit) =
            with(itemView) {
                binding.tvVideoTitle.text = item.title
                binding.tvViewCount.text = context.getString(R.string.views_count, item.viewCount)

                binding.root.setOnClickListener {
                    if (!item.isSelected) {
                        item.isSelected = true
                        setBackgroundColor(context.getColor(androidx.appcompat.R.color.dim_foreground_disabled_material_light))
                        onItemClicked(item, position)
                    }
                }


            }
    }

    companion object {
        private val VideoDC = object : DiffUtil.ItemCallback<Video>() {
            override fun areItemsTheSame(
                oldItem: Video,
                newItem: Video
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: Video,
                newItem: Video
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

}