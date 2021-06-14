package com.doublesymetrymusic.data.repository

import com.doublesymetrymusic.data.api.MusicSessionsService
import com.doublesymetrymusic.data.factory.DataFactory
import com.doublesymetrymusic.data.mapper.MusicSessionsMapper
import com.doublesymetrymusic.data.model.MusicSessionsData
import com.doublesymetrymusic.domain.datasource.DataSourceResultHolder
import com.doublesymetrymusic.domain.repository.MusicSessionsRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import retrofit2.Response
import kotlin.test.assertEquals

internal class MusicSessionsRepositoryTest {

  private lateinit var musicSessionsService: MusicSessionsService
  private lateinit var musicSessionsRepository: MusicSessionsRepository

  @Before
  fun setUp() {
    musicSessionsService = mockk(relaxed = true)

    musicSessionsRepository = MusicSessionsRepositoryImpl(
      musicSessionsService = musicSessionsService,
      mapper = MusicSessionsMapper()
    )
  }

  @Test
  fun testGetMusicSessions() = runBlocking {
    val expected = DataFactory.musicSessions

    every { musicSessionsRepository.searchMusicSessions() } returns flowOf(DataSourceResultHolder.success(DataFactory.musicSessions))

    val result = musicSessionsRepository.searchMusicSessions().first().data

    assertEquals(expected, result)
  }
}