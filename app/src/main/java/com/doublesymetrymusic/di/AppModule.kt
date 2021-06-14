package com.doublesymetrymusic.di

import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

object AppModule {

  fun load() {
    loadKoinModules(module {
    })
  }
}