package com.doublesymetrymusic.extensions

import android.view.View

fun View.gone() {
  this.visibility = View.GONE
}

fun View.visible() {
  this.visibility = View.VISIBLE
}

fun View.visibleOrGone(visible: Boolean) {
  if (visible) this.visible() else this.gone()
}

fun View.selected(selected: Boolean = true) {
  this.isSelected = selected
}