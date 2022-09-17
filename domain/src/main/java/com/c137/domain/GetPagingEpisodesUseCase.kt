package com.c137.domain

import androidx.paging.PagingData
import com.c137.domain.api.EpisodePagingRepository
import com.c137.domain.model.PresentationEpisode
import com.c137.domain.model.mapper.toPresentationModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@ViewModelScoped
class GetPagingEpisodesUseCase @Inject constructor(private val repository: EpisodePagingRepository) {

    fun execute(): Flow<PagingData<PresentationEpisode>> =
        repository.getPagingEpisode().map { it.toPresentationModel() }
}