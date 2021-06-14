package com.doublesymetrymusic.utils

import android.content.Context
import androidx.recyclerview.widget.GridLayoutManager

class GridLayoutManager(context: Context, columns: Int, private val animateItems: Boolean) : GridLayoutManager(context, columns) {
  override fun supportsPredictiveItemAnimations(): Boolean {
    return if (animateItems) true else super.supportsPredictiveItemAnimations()
  }
}