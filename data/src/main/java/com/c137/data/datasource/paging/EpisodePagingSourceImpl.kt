package com.c137.data.datasource.paging

import androidx.paging.PagingState
import com.c137.data.datasource.local.api.EpisodeDao
import com.c137.data.datasource.paging.api.EpisodePagingService
import com.c137.data.model.DataEpisode
import com.c137.data.model.mapper.dto.toDataModel
import com.c137.data.repository.api.EpisodePagingSource
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class EpisodePagingSourceImpl @Inject constructor(
    private val dao: EpisodeDao,
    private val service: EpisodePagingService
) : EpisodePagingSource() {

    override fun getRefreshKey(state: PagingState<Int, DataEpisode>) =
        state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DataEpisode> =
        try {
            val nextPage = params.key ?: 1
            val results = service.getEpisodesByPage(page = nextPage)
            LoadResult.Page(
                data = results.results.toDataModel(),
                prevKey = results.info.prev?.pageFromUrl(),
                nextKey = results.info.next?.pageFromUrl()
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
}