package com.c137.data.datasource.remote.api

import com.c137.data.model.dto.EpisodeDto
import com.c137.data.model.dto.ResultsDto
import retrofit2.http.GET

interface EpisodeService {

    @GET("episode")
    suspend fun getEpisodes(): ResultsDto<EpisodeDto>
}
