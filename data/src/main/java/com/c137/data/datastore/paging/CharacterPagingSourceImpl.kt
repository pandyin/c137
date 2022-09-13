package com.c137.data.datastore.paging

import android.net.Uri
import androidx.paging.PagingState
import com.c137.data.datastore.local.api.CharacterDao
import com.c137.data.datastore.paging.api.CharacterPagingService
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
                prevKey = results.info.prev?.let { extractPage(it) },
                nextKey = results.info.next?.let { extractPage(it) })
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    private fun extractPage(uriString: String): Int? =
        Uri.parse(uriString).getQueryParameter("page")?.toIntOrNull()
}