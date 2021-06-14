package com.doublesymetrymusic.homepage

import android.view.LayoutInflater
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.doublesymetrymusic.databinding.ViewSessionsLoadingStateBinding

class MusicSessionsLoadStateAdapter(private val retryOnClick: () -> Unit) : LoadStateAdapter<MusicSessionsLoadStateAdapter.ViewHolder>() {

  override fun onBindViewHolder(holder: ViewHolder, loadState: LoadState) {
    holder.apply {
      bind(loadState)
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): ViewHolder {
    return ViewHolder(ViewSessionsLoadingStateBinding.inflate(LayoutInflater.from(parent.context), parent, false))
  }

  inner class ViewHolder(private val binding: ViewSessionsLoadingStateBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(loadState: LoadState) {

      binding.loadState = loadState
      binding.retryOnClick = OnClickListener { retryOnClick() }
      binding.executePendingBindings()

    }
  }
}