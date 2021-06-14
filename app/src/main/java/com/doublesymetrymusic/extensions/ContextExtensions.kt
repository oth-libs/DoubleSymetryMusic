package com.doublesymetrymusic.extensions

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes

fun Context.showToast(@StringRes stringRes: Int) {
  Toast.makeText(this, stringRes, Toast.LENGTH_SHORT).show()
}
