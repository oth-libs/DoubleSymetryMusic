package com.doublesymetrymusic.domain.usecase

import androidx.paging.PagingData
import com.doublesymetrymusic.data.factory.DataFactory
import com.doublesymetrymusic.domain.repository.MusicSessionsRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class LoadMusicSessionsUseCaseTest {

  private val musicSessionsRepository: MusicSessionsRepository = mockk()

  private val loadMusicSessions = PagingData.from(DataFactory.musicSessions.getMusicSessions()!!)

  @Before
  fun init() {
    coEvery { musicSessionsRepository.loadMusicSessionsPage() } returns flowOf(loadMusicSessions)
  }

  @Test
  fun testSearchMusicSessionsUseCase() = runBlocking {
    val useCase = LoadMusicSessionsPageUseCase(repository = musicSessionsRepository)

    val searchMusicSessionsResult = useCase.execute().first()

    assertEquals(loadMusicSessions, searchMusicSessionsResult)
  }
}