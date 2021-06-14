package com.doublesymetrymusic.homepage

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import com.doublesymetrymusic.BaseFragment
import com.doublesymetrymusic.R
import com.doublesymetrymusic.databinding.FragmentHomepageBinding
import com.doublesymetrymusic.domain.model.MusicSession
import com.doublesymetrymusic.extensions.setup
import com.doublesymetrymusic.presentation.homepage.AdapterType
import com.doublesymetrymusic.presentation.homepage.HomePageViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@FlowPreview
class HomePageFragment : BaseFragment<FragmentHomepageBinding, HomePageViewModel>(
  layoutId = R.layout.fragment_homepage,
) {

  private val musicSessionsPageAdapter by lazy { MusicSessionsPageAdapter() }
  private val musicSessionsLoadStateAdapter by lazy { MusicSessionsLoadStateAdapter { musicSessionsPageAdapter.retry() } }
  private val searchSessionsAdapter by lazy { SearchSessionsAdapter(viewModel.searchMusicSessionsData) }

  override fun setupBinding() {
    binding.viewModel = viewModel

    setupViews()
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    observeViewModelCalls()
  }

  private fun setupViews() {
    binding.ifSearch.getTextChangeFlow()
      .debounce(500)
      .onEach { viewModel.handleNewSearch(it) }
      .launchIn(lifecycleScope)

    binding.rvSongs.setup(
      animateItems = true,
      columnCount = 2,
      customSpanSize = { position -> if (position == musicSessionsPageAdapter.itemCount && musicSessionsLoadStateAdapter.itemCount > 0) 2 else 1 }
    )
    // set paging adapter
    setupForPaging()
  }

  private fun observeViewModelCalls() {
    viewModel.pageData.observe(viewLifecycleOwner, ::newPageDataReceived)

    viewModel.adapterType.observe(viewLifecycleOwner, ::setAdapter)

    viewModel.searchResponse.observe(viewLifecycleOwner, ::newSearchDataReceived)
  }

  /**
   * Setup the list with the paging adapter on start + on search query empty
   */
  private fun setupForPaging() {
    binding.rvSongs.adapter = musicSessionsPageAdapter.withLoadStateFooter(musicSessionsLoadStateAdapter)
  }

  /**
   * Setup the list with the search result adapter
   */
  private fun setupForSearch() {
    if (binding.rvSongs.adapter !is SearchSessionsAdapter) {
      binding.rvSongs.adapter = searchSessionsAdapter
    }
  }

  /**
   * Submit new data to the paging adapter
   */
  private fun newPageDataReceived(pageData: PagingData<MusicSession>) {
    lifecycleScope.launch {
      musicSessionsPageAdapter.submitData(pageData)
    }
  }

  /**
   * Set the right [AdapterType]
   */
  private fun setAdapter(adapterType: AdapterType) {
    when (adapterType) {
      AdapterType.BROWSE -> {
        setupForPaging()
      }
      AdapterType.SEARCH -> {
        setupForSearch()
      }
    }
  }

  /**
   * Notify the adapter that a new search result is received
   */
  private fun newSearchDataReceived(@Suppress("UNUSED_PARAMETER") unit: Unit) {
    binding.rvSongs.adapter?.notifyDataSetChanged()
  }
}