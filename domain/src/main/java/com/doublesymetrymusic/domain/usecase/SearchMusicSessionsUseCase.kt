package com.doublesymetrymusic.domain.usecase

import com.doublesymetrymusic.domain.datasource.DataSourceResultHolder
import com.doublesymetrymusic.domain.model.MusicSessions
import com.doublesymetrymusic.domain.repository.MusicSessionsRepository
import kotlinx.coroutines.flow.Flow

class SearchMusicSessionsUseCase(private val repository: MusicSessionsRepository) {

  fun execute(): Flow<DataSourceResultHolder<MusicSessions>> = repository.searchMusicSessions()

}
