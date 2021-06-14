package com.doublesymetrymusic.domain.di

import com.doublesymetrymusic.domain.usecase.LoadMusicSessionsPageUseCase
import com.doublesymetrymusic.domain.usecase.SearchMusicSessionsUseCase
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

object DomainModule {

  fun load() {
    loadKoinModules(module {
      single { LoadMusicSessionsPageUseCase(repository = get()) }
      single { SearchMusicSessionsUseCase(repository = get()) }
    })
  }
}