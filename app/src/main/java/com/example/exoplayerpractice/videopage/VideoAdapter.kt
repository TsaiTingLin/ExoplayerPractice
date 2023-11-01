package com.example.exoplayerpractice.videopage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.exoplayerpractice.databinding.FragmentVideoBinding

class VideoAdapter(private val datas: List<String>) : Adapter<VideoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        return VideoViewHolder(FragmentVideoBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount() = datas.size

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        holder.bindUrl(datas[position])
    }
}

class VideoViewHolder(private val binding: FragmentVideoBinding) : ViewHolder(binding.root) {

    fun bindUrl(url: String) {
        val mPlayer = ExoPlayer.Builder(binding.root.context).build()
        binding.exoPlayer.apply {
            player = mPlayer
            setPadding(0, 10, 0, 0)
            val params = layoutParams
            params.height = 800
            layoutParams = params
        }

        mPlayer.apply {
            addMediaItem(MediaItem.fromUri(url))
            prepare()
            playWhenReady = false
        }
    }

}