package com.c137.data.datasource.paging

import android.net.Uri
import androidx.paging.PagingState
import com.c137.data.datasource.local.api.CharacterDao
import com.c137.data.datasource.paging.api.CharacterPagingService
import com.c137.data.model.CharacterWithOriginAndLastKnown
import com.c137.data.model.mapper.dto.toDataModel
import com.c137.data.repository.api.CharacterPagingSource
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class CharacterPagingSourceImpl @Inject constructor(
    private val dao: CharacterDao,
    private val service: CharacterPagingService
) : CharacterPagingSource() {

    override fun getRefreshKey(state: PagingState<Int, CharacterWithOriginAndLastKnown>) =
        state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterWithOriginAndLastKnown> {
        return try {
            val nextPage = params.key ?: 1
            val results = service.getCharactersByPage(page = nextPage)
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