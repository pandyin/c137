package com.c137.domain

import com.c137.domain.api.EpisodeRepository
import com.c137.domain.model.PresentationEpisode
import com.c137.domain.model.mapper.toPresentationModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@ViewModelScoped
class GetEpisodesUseCase @Inject constructor(private val repository: EpisodeRepository) {

    fun execute(): Flow<List<PresentationEpisode>> =
        repository.getEpisodes().map { it.map { episode -> episode.toPresentationModel() } }
}