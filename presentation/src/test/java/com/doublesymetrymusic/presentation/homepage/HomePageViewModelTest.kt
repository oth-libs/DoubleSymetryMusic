package com.doublesymetrymusic.presentation.homepage

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.doublesymetrymusic.data.factory.DataFactory
import com.doublesymetrymusic.domain.datasource.DataSourceResultHolder
import com.doublesymetrymusic.domain.usecase.LoadMusicSessionsPageUseCase
import com.doublesymetrymusic.domain.usecase.SearchMusicSessionsUseCase
import com.doublesymetrymusic.presentation.TestCoroutineRule
import com.doublesymetrymusic.presentation.getOrAwaitValue
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

class HomePageViewModelTest {

  @get:Rule
  val rule = InstantTaskExecutorRule()

  @get:Rule
  val testCoroutineRule = TestCoroutineRule()

  private val loadMusicSessionsPageUseCase = mockk<LoadMusicSessionsPageUseCase>()
  private val searchMusicSessionsUseCase = mockk<SearchMusicSessionsUseCase>()
  private val application = mockk<Application>(relaxed = true)

  private val viewModel = HomePageViewModel(loadMusicSessionsPageUseCase, searchMusicSessionsUseCase, application)

  @Test
  fun testHandleNewSearchEmpty() {
    viewModel.handleNewSearch("")
    assertEquals(viewModel.adapterType.getOrAwaitValue(), AdapterType.BROWSE)
  }

  @Test
  fun testHandleNewSearchNotEmpty() {
    coEvery { searchMusicSessionsUseCase.execute() } returns flowOf(DataSourceResultHolder.error())

    viewModel.getData().addAll(DataFactory.musicSessions.getMusicSessions()!!)
    assertEquals(viewModel.searchMusicSessionsData.size, 1)

    viewModel.handleNewSearch("ABC")
    assertEquals(viewModel.adapterType.getOrAwaitValue(), AdapterType.SEARCH)
    assertEquals(viewModel.searchMusicSessionsData.size, 0)

    coVerify(exactly = 1) { searchMusicSessionsUseCase.execute() }
  }

  @Test
  fun testSearchMusicSessions() {
    coEvery { searchMusicSessionsUseCase.execute() } returns flowOf(DataSourceResultHolder.success(DataFactory.musicSessions))

    viewModel.handleNewSearch("ABC")

    coVerify(exactly = 1) { searchMusicSessionsUseCase.execute() }

    assertEquals(viewModel.searchMusicSessionsData.size, 3)
  }

}