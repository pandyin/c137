package com.c137.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.c137.data.model.mapper.toDomainModel
import com.c137.data.repository.api.EpisodePagingSource
import com.c137.domain.api.EpisodePagingRepository
import com.c137.domain.model.DomainEpisode
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private const val PAGE_SIZE = 20

@ViewModelScoped
class EpisodePagingRepositoryImpl @Inject constructor(private val pagingSource: EpisodePagingSource) :
    EpisodePagingRepository {

    override fun getPagingEpisode(): Flow<PagingData<DomainEpisode>> =
        Pager(config = PagingConfig(pageSize = PAGE_SIZE)) { pagingSource }.flow.map { it.toDomainModel() }
}