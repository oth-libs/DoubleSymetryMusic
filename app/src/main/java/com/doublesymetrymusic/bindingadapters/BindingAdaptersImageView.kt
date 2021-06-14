package com.doublesymetrymusic.bindingadapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("app:loadRemoteImage") fun loadRemoteImage(imageView: ImageView, imageUrl: String?) {
  imageUrl?.let {
    Picasso.get()
      .load(imageUrl)
      .into(imageView)
  }
}
