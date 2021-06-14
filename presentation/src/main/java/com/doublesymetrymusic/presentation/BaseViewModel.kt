package com.doublesymetrymusic.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleObserver
import com.doublesymetrymusic.presentation.livedata.SingleLiveEvent

open class BaseViewModel(application: Application) : AndroidViewModel(application), LifecycleObserver {
  val internetErrorLiveData = SingleLiveEvent<Unit>()
  val genericErrorLiveData = SingleLiveEvent<Unit>()
  val showMessageResId = SingleLiveEvent<@androidx.annotation.StringRes Int?>()
}
