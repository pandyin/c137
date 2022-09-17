package com.c137.domain.api

import androidx.paging.PagingData
import com.c137.domain.model.DomainEpisode
import kotlinx.coroutines.flow.Flow

interface EpisodePagingRepository {

    fun getPagingEpisode(): Flow<PagingData<DomainEpisode>>
}