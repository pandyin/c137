package com.c137.data.repository.api

import com.c137.data.model.DataEpisode
import com.c137.data.model.dto.EpisodeDto
import kotlinx.coroutines.flow.Flow

interface EpisodeLocalDataSource {

    suspend fun insertEpisodes(episodes: List<EpisodeDto>)

    fun getEpisodes(): Flow<List<DataEpisode>>
}