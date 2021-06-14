package com.doublesymetrymusic.domain.usecase

import com.doublesymetrymusic.data.factory.DataFactory
import com.doublesymetrymusic.domain.datasource.DataSourceResultHolder
import com.doublesymetrymusic.domain.repository.MusicSessionsRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class SearchMusicSessionsUseCaseTest {

  private val musicSessionsRepository: MusicSessionsRepository = mockk()

  private val searchMusicSessions = DataSourceResultHolder.success(DataFactory.musicSessions)

  @Before
  fun init() {
    coEvery { musicSessionsRepository.searchMusicSessions() } returns flowOf(searchMusicSessions)
  }

  @Test
  fun testSearchMusicSessionsUseCase() = runBlocking {
    val useCase = SearchMusicSessionsUseCase(repository = musicSessionsRepository)

    val searchMusicSessionsResult = useCase.execute().first()

    assertEquals(searchMusicSessions, searchMusicSessionsResult)
  }
}