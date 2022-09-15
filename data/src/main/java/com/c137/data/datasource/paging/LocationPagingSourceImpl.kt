package com.c137.data.datasource.paging

import android.net.Uri
import androidx.paging.PagingState
import com.c137.data.datasource.local.api.LocationDao
import com.c137.data.datasource.paging.api.LocationPagingService
import com.c137.data.model.DataLocation
import com.c137.data.model.mapper.dto.toDataModel
import com.c137.data.repository.api.LocationPagingSource
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class LocationPagingSourceImpl @Inject constructor(
    private val dao: LocationDao,
    private val service: LocationPagingService
) : LocationPagingSource() {

    override fun getRefreshKey(state: PagingState<Int, DataLocation>) = state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DataLocation> {
        return try {
            val nextPage = params.key ?: 1
            val results = service.getLocationsByPage(page = nextPage)
            LoadResult.Page(
                data = results.results.toDataModel(),
                prevKey = results.info.prev?.let { pageFromUrl(it) },
                nextKey = results.info.next?.let { pageFromUrl(it) })
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    private fun pageFromUrl(uriString: String): Int? =
        Uri.parse(uriString).getQueryParameter("page")?.toIntOrNull()
}