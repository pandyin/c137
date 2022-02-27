package com.c137.character.data.repository.datastore.remote.api

import com.c137.character.data.model.Character
import com.c137.character.data.model.CharactersDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterApi {

    @GET("character")
    fun getCharactersByStatus(
        @Query("page") page: Int,
        @Query("status") status: String
    ): Call<CharactersDto>

    @GET("character")
    fun getCharactersByPage(@Query("page") page: Int): Call<CharactersDto>

    @GET("character/{id}")
    fun getCharacterById(@Path("id") id: Int): Call<Character>
}