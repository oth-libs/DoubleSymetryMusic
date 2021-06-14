package com.doublesymetrymusic.data.repository

import androidx.paging.PagingData
import com.doublesymetrymusic.data.api.MusicSessionsService
import com.doublesymetrymusic.data.datasource.RemoteDataSource
import com.doublesymetrymusic.data.datasource.resultFlow
import com.doublesymetrymusic.data.datasource.resultPagingFlow
import com.doublesymetrymusic.data.mapper.Mapper
import com.doublesymetrymusic.data.model.MusicSessionsData
import com.doublesymetrymusic.domain.datasource.DataSourceResultHolder
import com.doublesymetrymusic.domain.model.MusicSession
import com.doublesymetrymusic.domain.model.MusicSessions
import com.doublesymetrymusic.domain.repository.MusicSessionsRepository
import kotlinx.coroutines.flow.Flow


internal class MusicSessionsRepositoryImpl(
  private val musicSessionsService: MusicSessionsService,
  private val mapper: Mapper<MusicSessionsData, MusicSessions>
) : MusicSessionsRepository, RemoteDataSource() {


  override fun searchMusicSessions(): Flow<DataSourceResultHolder<MusicSessions>> {
    return resultFlow(
      networkCall = {
        getResult(
          call = { musicSessionsService.searchMusicSessions() },
          transform = { mapper.map(it) }
        )
      }
    )
  }

  override fun loadMusicSessionsPage(): Flow<PagingData<MusicSession>> {
    return resultPagingFlow(
      networkCall = {
        getResult(
          call = { musicSessionsService.getMusicSessions() },
          transform = { mapper.map(it) }
        )
      },
      extractListItems = { it?.getMusicSessions() ?: emptyList() }
    )
  }

}