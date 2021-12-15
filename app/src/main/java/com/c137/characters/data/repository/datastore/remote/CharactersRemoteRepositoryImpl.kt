package com.c137.characters.data.repository.datastore.remote

import com.c137.characters.data.model.Character
import com.c137.characters.data.repository.datastore.remote.api.CharactersApi
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.rxjava3.core.Single
import java.lang.reflect.Type


class CharactersRemoteRepositoryImpl(private val remoteApi: CharactersApi) : CharactersRemoteRepository {

    override fun getCharacters(): Single<List<Character>> {
        return Single.fromCallable {
            remoteApi.getCharactersByPage(1)
                .execute()
                .body()
        }.map {
            val type: Type = object : TypeToken<List<Character>>() {}.type
            Gson().fromJson(it!!["results"].asJsonArray, type)
        }
    }
}