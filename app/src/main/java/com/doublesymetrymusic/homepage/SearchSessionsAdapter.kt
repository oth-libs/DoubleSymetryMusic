package com.doublesymetrymusic.homepage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.doublesymetrymusic.databinding.ItemViewSessionBinding
import com.doublesymetrymusic.domain.model.MusicSession

class SearchSessionsAdapter(
  private val data: List<MusicSession>
) : RecyclerView.Adapter<SearchSessionsAdapter.ViewHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
    return ViewHolder(ItemViewSessionBinding.inflate(LayoutInflater.from(parent.context), parent, false))
  }

  override fun getItemCount() = data.size

  private fun getItem(position: Int) = data[position]

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder.apply {
      bind(getItem(position))
    }
  }

  inner class ViewHolder(private val binding: ItemViewSessionBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(musicSession: MusicSession) {
      binding.apply {
        this.musicSession = musicSession
        executePendingBindings()
      }
    }
  }
}

