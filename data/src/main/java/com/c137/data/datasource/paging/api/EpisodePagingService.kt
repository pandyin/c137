package com.c137.data.datasource.paging.api

import com.c137.data.model.dto.EpisodeDto
import com.c137.data.model.dto.ResultsDto
import retrofit2.http.GET
import retrofit2.http.Query

interface EpisodePagingService {

    @GET("episode")
    suspend fun getEpisodesByPage(@Query("page") page: Int): ResultsDto<EpisodeDto>
}