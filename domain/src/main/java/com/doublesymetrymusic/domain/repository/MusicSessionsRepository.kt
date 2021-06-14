package com.doublesymetrymusic.domain.repository

import androidx.paging.PagingData
import com.doublesymetrymusic.domain.datasource.DataSourceResultHolder
import com.doublesymetrymusic.domain.model.MusicSession
import com.doublesymetrymusic.domain.model.MusicSessions
import kotlinx.coroutines.flow.Flow

interface MusicSessionsRepository {

  fun searchMusicSessions(): Flow<DataSourceResultHolder<MusicSessions>>

  fun loadMusicSessionsPage(): Flow<PagingData<MusicSession>>
}