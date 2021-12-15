package com.c137.characters.data.repository.datastore.remote.api

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CharactersApi {

    @GET("character")
    fun getCharactersByPage(@Query("page") page: Int): Call<JsonObject>
}