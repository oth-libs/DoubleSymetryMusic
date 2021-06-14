package com.doublesymetrymusic.bindingadapters

import android.view.View
import androidx.databinding.BindingAdapter
import com.doublesymetrymusic.extensions.visibleOrGone

@BindingAdapter("app:visibleOrGone") fun visibleOrGone(view: View, visible: Boolean?) {
  visible ?: return
  view.visibleOrGone(visible)
}



