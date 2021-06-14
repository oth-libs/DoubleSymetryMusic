package com.doublesymetrymusic.bindingadapters

import androidx.databinding.BindingAdapter
import com.doublesymetrymusic.domain.datasource.DataSourceResultHolder
import com.doublesymetrymusic.views.InputField

@BindingAdapter("app:setStatus") fun setStatus(inputField: InputField, status: DataSourceResultHolder.Status?) {
  status?.let {
    inputField.toggleLoading(status == DataSourceResultHolder.Status.IN_PROGRESS)
  }
}
