package com.c137.data.datastore.paging.api

import com.c137.data.model.dto.CharacterDto
import com.c137.data.model.dto.ResultsDto
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterPagingService {

    @GET("character")
    suspend fun getCharactersByPage(@Query("page") page: Int): ResultsDto<CharacterDto>
}