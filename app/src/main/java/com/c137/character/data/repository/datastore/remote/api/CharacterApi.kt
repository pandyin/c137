package com.c137.character.data.repository.datastore.remote.api

import com.c137.character.data.model.dto.CharacterDto
import com.c137.character.data.model.dto.LocationDto
import com.c137.character.data.model.dto.ResultsDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterApi {

    @GET("character")
    fun getCharactersByStatus(
        @Query("page") page: Int,
        @Query("status") status: String
    ): Call<ResultsDto<CharacterDto>>

    @GET("character")
    fun getCharactersByPage(@Query("page") page: Int): Call<ResultsDto<CharacterDto>>

    @GET("character/{id}")
    fun getCharacterById(@Path("id") id: Int): Call<CharacterDto>

    @GET("location")
    fun getLocation(@Query("page") page: Int): Call<ResultsDto<LocationDto>>
}