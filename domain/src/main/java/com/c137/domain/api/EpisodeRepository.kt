package com.c137.domain.api

import com.c137.domain.model.DomainEpisode
import kotlinx.coroutines.flow.Flow

interface EpisodeRepository {

    fun getEpisodes(): Flow<List<DomainEpisode>>
}