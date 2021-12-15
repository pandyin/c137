package com.c137.characters.data.repository.datastore.remote.api

import com.c137.characters.data.model.Characters
import retrofit2.Call
import retrofit2.http.GET

interface CharactersApi {

    @GET("starships/")
    fun getAllCharacters(): Call<Characters>
}