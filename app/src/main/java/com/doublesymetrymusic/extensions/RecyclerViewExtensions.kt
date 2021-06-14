package com.doublesymetrymusic.extensions

import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.doublesymetrymusic.R
import com.doublesymetrymusic.utils.GridLayoutManager
import com.doublesymetrymusic.utils.GridSpacingItemDecoration

/**
 * setup the recyclerview with a custom [LayoutManager] and add [DividerItemDecoration] depending on [removeDivider]
 *
 * [customSpanSize] - if there is need to define a different column count for specific positions
 *
 */
fun RecyclerView.setup(removeDivider: Boolean = false, animateItems: Boolean = false, columnCount: Int = 2, customSpanSize: ((Int) -> Int)?): GridLayoutManager {
  layoutManager = GridLayoutManager(context, columnCount, animateItems).apply {
    spanSizeLookup = object : androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup() {
      override fun getSpanSize(position: Int): Int {
        return customSpanSize?.invoke(position) ?: columnCount
      }
    }
  }

  // remove existing item decorators
  while (itemDecorationCount > 0) {
    removeItemDecorationAt(0)
  }

  // set item decorator
  if (!removeDivider) {
    addItemDecoration(GridSpacingItemDecoration(columnCount, resources.getDimension(R.dimen.grid_spacing).toInt(), true))
  }

  return layoutManager as GridLayoutManager
}