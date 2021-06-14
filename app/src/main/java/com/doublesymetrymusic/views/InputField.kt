package com.doublesymetrymusic.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.doublesymetrymusic.R
import com.doublesymetrymusic.extensions.selected
import com.doublesymetrymusic.extensions.textChangeFlow
import com.doublesymetrymusic.extensions.visibleOrGone
import kotlinx.android.synthetic.main.view_input_field.view.etField
import kotlinx.android.synthetic.main.view_input_field.view.ivSearch
import kotlinx.android.synthetic.main.view_input_field.view.pbLoading
import kotlinx.android.synthetic.main.view_input_field.view.vContainer
import kotlinx.coroutines.ExperimentalCoroutinesApi

class InputField @JvmOverloads constructor(
  context: Context,
  attrs: AttributeSet? = null,
  defStyle: Int = 0
) : LinearLayout(context, attrs, defStyle) {

  init {
    LayoutInflater.from(context).inflate(R.layout.view_input_field, this, true)

    attrs?.let {
      val typedArray = context.obtainStyledAttributes(it, R.styleable.InputField)

      val hint = typedArray.getString(R.styleable.InputField_android_hint)
      etField.hint = hint

      typedArray.recycle()
    }

    etField.setOnFocusChangeListener { _, hasFocus ->
      vContainer.selected(hasFocus)
    }
  }

  fun toggleLoading(isLoading: Boolean) {
    ivSearch.visibleOrGone(!isLoading)
    pbLoading.visibleOrGone(isLoading)
  }

  @ExperimentalCoroutinesApi fun getTextChangeFlow() = etField.textChangeFlow()
}