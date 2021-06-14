package com.doublesymetrymusic.bindingadapters

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.paging.LoadState
import com.doublesymetrymusic.R
import com.doublesymetrymusic.domain.datasource.DataSourceException
import com.doublesymetrymusic.domain.datasource.ErrorException
import com.doublesymetrymusic.domain.datasource.NoInternetException

@BindingAdapter("app:setLoadStateErrorText") fun setLoadStateErrorText(textView: TextView, loadState: LoadState?) {
  if (loadState == null || loadState !is LoadState.Error) {
    textView.text = String()
    return
  }

  textView.apply {
    setText(
      when (loadState.error as DataSourceException) {
        is NoInternetException -> {
          R.string.error_internet
        }
        is ErrorException -> {
          R.string.error_generic
        }
      }
    )
  }
}
