package com.doublesymetrymusic.homepage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.doublesymetrymusic.databinding.ItemViewSessionBinding
import com.doublesymetrymusic.domain.model.MusicSession

class MusicSessionsPageAdapter : PagingDataAdapter<MusicSession, MusicSessionsPageAdapter.ViewHolder>(DiffCallBack()) {

  class DiffCallBack : DiffUtil.ItemCallback<MusicSession>() {
    override fun areItemsTheSame(oldItem: MusicSession, newItem: MusicSession) = false
    override fun areContentsTheSame(oldItem: MusicSession, newItem: MusicSession) = false
  }

  override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
    return ViewHolder(ItemViewSessionBinding.inflate(LayoutInflater.from(parent.context), parent, false))
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder.apply {
      bind(getItem(position))
    }
  }

  inner class ViewHolder(private val binding: ItemViewSessionBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(musicSession: MusicSession?) {
      musicSession?.let {
        binding.apply {
          this.musicSession = musicSession
          executePendingBindings()
        }
      }
    }
  }

}

