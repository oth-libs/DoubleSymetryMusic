package com.doublesymetrymusic.data.datasource

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.recyclerview.widget.RecyclerView
import com.doublesymetrymusic.domain.datasource.DataSourceResultHolder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 *
 * [API_MODEL] - The model of the api result
 *
 * [networkCall] - Calls the network and implicitly checks for internet connection and returns the result wrapped in a [DataSourceResultHolder]
 *
 * Function notify UI about:
 * [DataSourceResultHolder.Status.SUCCESS] - with data from database
 * [DataSourceResultHolder.Status.ERROR] - if error has occurred`
 * [DataSourceResultHolder.Status.IN_PROGRESS]
 */
fun <API_MODEL> resultFlow(
  networkCall: suspend () -> DataSourceResultHolder<API_MODEL>
): Flow<DataSourceResultHolder<API_MODEL>> {
  return flow {

    // 1- notify the process started
    emit(DataSourceResultHolder.inProgress())

    // 2- get remote result, will also hold success status
    val responseStatus = networkCall.invoke()

    emit(responseStatus)
  }
}

/**
 * get the result of a paging api and prepare to be used for [RecyclerView]
 *
 * [API_MODEL] - The model of the api result
 * [LIST_MODEL] - The model for the list item
 *
 * [networkCall] - Calls the network and implicitly checks for internet connection and returns the result wrapped in a [DataSourceResultHolder]
 * [extractListItems] - Extracts the list of [LIST_MODEL] from the [API_MODEL]
 */
fun <API_MODEL, LIST_MODEL : Any> resultPagingFlow(
  networkCall: suspend () -> DataSourceResultHolder<API_MODEL>,
  extractListItems: (API_MODEL?) -> List<LIST_MODEL>
): Flow<PagingData<LIST_MODEL>> {
  return Pager(

    config = PagingConfig(
      pageSize = 1,
      enablePlaceholders = false
    ),

    pagingSourceFactory = {
      object : PagingSource<Int, LIST_MODEL>() {

        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, LIST_MODEL> {
          val pageIndex = params.key ?: 1
          val response = networkCall.invoke()

          return when (response.status) {
            DataSourceResultHolder.Status.SUCCESS -> {
              val responseData = response.data

              LoadResult.Page(
                data = extractListItems.invoke(responseData),
                prevKey = null,
                nextKey = if (pageIndex == 5) null else pageIndex + 1
              )
            }
            else -> {
              LoadResult.Error(response.exception!!)
            }
          }
        }

        // return anchorPosition directly since there is no placeholders or prevKey
        override fun getRefreshKey(state: PagingState<Int, LIST_MODEL>) = state.anchorPosition
      }
    }
  ).flow
}