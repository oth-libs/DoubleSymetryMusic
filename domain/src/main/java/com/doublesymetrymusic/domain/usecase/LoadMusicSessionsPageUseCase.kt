package com.doublesymetrymusic.domain.usecase

import androidx.paging.PagingData
import com.doublesymetrymusic.domain.model.MusicSession
import com.doublesymetrymusic.domain.repository.MusicSessionsRepository
import kotlinx.coroutines.flow.Flow

class LoadMusicSessionsPageUseCase(private val repository: MusicSessionsRepository) {

  fun execute(): Flow<PagingData<MusicSession>> = repository.loadMusicSessionsPage()

}
