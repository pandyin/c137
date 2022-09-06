package com.c137.data.datastore.remote.api

import com.c137.data.model.dto.CharacterDto
import com.c137.data.model.dto.ResultsDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterService {

    @GET("character")
    fun getCharactersByPage(@Query("page") page: Int): ResultsDto<CharacterDto>

    @GET("character/{id}")
    suspend fun getCharacterById(@Path("id") id: Int): CharacterDto
}