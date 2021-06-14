package com.doublesymetrymusic.presentation.homepage

import android.app.Application
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.doublesymetrymusic.domain.datasource.DataSourceResultHolder
import com.doublesymetrymusic.domain.model.MusicSession
import com.doublesymetrymusic.domain.usecase.LoadMusicSessionsPageUseCase
import com.doublesymetrymusic.domain.usecase.SearchMusicSessionsUseCase
import com.doublesymetrymusic.presentation.BaseViewModel
import com.doublesymetrymusic.presentation.livedata.SingleLiveEvent
import com.doublesymetrymusic.presentation.utils.isUiTest
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomePageViewModel(
  private val loadMusicSessionsPageUseCase: LoadMusicSessionsPageUseCase,
  private val searchMusicSessionsUseCase: SearchMusicSessionsUseCase,
  application: Application
) : BaseViewModel(application) {

  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // __      ___                 _      _           _____        _
  // \ \    / (_)               | |    (_)         |  __ \      | |
  //  \ \  / / _  _____      __ | |     ___   _____| |  | | __ _| |_ __ _
  //   \ \/ / | |/ _ \ \ /\ / / | |    | \ \ / / _ \ |  | |/ _` | __/ _` |
  //    \  /  | |  __/\ V  V /  | |____| |\ V /  __/ |__| | (_| | || (_| |
  //     \/   |_|\___| \_/\_/   |______|_| \_/ \___|_____/ \__,_|\__\__,_|
  //
  //Font Name: Big
  private val _pageData = SingleLiveEvent<PagingData<MusicSession>>()
  val pageData: LiveData<PagingData<MusicSession>> = _pageData

  private val _adapterType = SingleLiveEvent<AdapterType>()
  val adapterType: LiveData<AdapterType> = _adapterType

  private val _searchStatus = MutableLiveData<DataSourceResultHolder.Status>()
  val searchStatus: LiveData<DataSourceResultHolder.Status> = _searchStatus

  private val _searchResponse = SingleLiveEvent<Unit>()
  val searchResponse: LiveData<Unit> = _searchResponse

  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  //  _____        _
  // |  __ \      | |
  // | |  | | __ _| |_ __ _
  // | |  | |/ _` | __/ _` |
  // | |__| | (_| | || (_| |
  // |_____/ \__,_|\__\__,_|
  //
  //Font Name: Big
  private val _searchMusicSessionsData = mutableListOf<MusicSession>()
  val searchMusicSessionsData: List<MusicSession> = _searchMusicSessionsData

  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  //  _    _           _____
  // | |  | |         / ____|
  // | |  | |___  ___| |     __ _ ___  ___
  // | |  | / __|/ _ \ |    / _` / __|/ _ \
  // | |__| \__ \  __/ |___| (_| \__ \  __/
  //  \____/|___/\___|\_____\__,_|___/\___|
  //
  //Font Name: Big
  /**
   * Start app with loading the first page
   */
  @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
  private fun loadInitialPage() {
    viewModelScope.launch {
      loadMusicSessionsPageUseCase.execute().collect {
        _pageData.value = it
      }
    }
  }

  /**
   * Launch a search query
   */
  private fun searchMusicSessions() {
    viewModelScope.launch {
      searchMusicSessionsUseCase.execute().collect { response ->
        _searchStatus.value = response.status

        when (response.status) {
          DataSourceResultHolder.Status.ERROR -> {
            genericErrorLiveData.value = Unit
          }

          DataSourceResultHolder.Status.NO_INTERNET -> {
            internetErrorLiveData.value = Unit
          }

          DataSourceResultHolder.Status.SUCCESS -> {
            response.data?.let { sessionsModel ->
              _searchMusicSessionsData.apply { addAll(sessionsModel.getMusicSessions()?.shuffled() ?: emptyList()) }
              _searchResponse.value = Unit
            }
          }

          else -> Unit
        }
      }
    }
  }

  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  fun handleNewSearch(searchTerm: String) {
    if (searchTerm.isEmpty()) {
      _adapterType.value = AdapterType.BROWSE
    } else {
      _adapterType.value = AdapterType.SEARCH

      _searchMusicSessionsData.clear()
      searchMusicSessions()
    }
  }

  internal fun getData(): MutableList<MusicSession> {
    if (!isUiTest()) throw RuntimeException()

    return _searchMusicSessionsData
  }
}