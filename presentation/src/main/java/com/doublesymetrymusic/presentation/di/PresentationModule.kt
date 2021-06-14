package com.doublesymetrymusic.presentation.di

import com.doublesymetrymusic.presentation.homepage.HomePageViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

object PresentationModule {

  fun load() {
    loadKoinModules(module {
      viewModel { HomePageViewModel(loadMusicSessionsPageUseCase = get(), searchMusicSessionsUseCase = get(), application = get()) }
    })
  }
}