package com.c137.data.datasource.remote.api

import com.c137.data.model.dto.CharacterDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CharacterService {

    @GET("character/{id}")
    suspend fun getCharacterById(@Path("id") id: Int): CharacterDto
}
