package com.doublesymetrymusic

import android.app.Application
import com.doublesymetrymusic.data.di.DataModule
import com.doublesymetrymusic.di.AppModule
import com.doublesymetrymusic.domain.di.DomainModule
import com.doublesymetrymusic.presentation.di.PresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

@Suppress("unused")
class DSApplication : Application() {

  override fun onCreate() {
    super.onCreate()

    startKoin {
      androidContext(this@DSApplication)
    }

    loadModules()
  }

  private fun loadModules() {
    AppModule.load()
    PresentationModule.load()
    DomainModule.load()
    DataModule.load()
  }
}
