package com.doublesymetrymusic.data.di

import com.doublesymetrymusic.data.api.MusicSessionsService
import com.doublesymetrymusic.data.api.RetrofitFactory
import com.doublesymetrymusic.data.mapper.MusicSessionsMapper
import com.doublesymetrymusic.data.repository.MusicSessionsRepositoryImpl
import com.doublesymetrymusic.domain.repository.MusicSessionsRepository
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import retrofit2.Retrofit

object DataModule {

  fun load() {
    loadKoinModules(module {
      //  retrofit
      single { RetrofitFactory().build() }
      single { (get() as Retrofit).create(MusicSessionsService::class.java) }

      single<MusicSessionsRepository> { MusicSessionsRepositoryImpl(musicSessionsService = get(), mapper = MusicSessionsMapper()) }
    })
  }
}