package com.c137.data.repository.api

import com.c137.data.model.dto.EpisodeDto
import com.c137.data.model.dto.ResultsDto

interface EpisodeRemoteDataSource {

    suspend fun getEpisodes(): ResultsDto<EpisodeDto>
}