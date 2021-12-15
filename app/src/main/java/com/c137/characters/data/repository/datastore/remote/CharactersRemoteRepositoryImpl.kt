package com.c137.characters.data.repository.datastore.remote

import com.c137.characters.data.model.Characters
import com.c137.characters.data.repository.datastore.remote.api.CharactersApi
import io.reactivex.rxjava3.core.Single

class CharactersRemoteRepositoryImpl(private val remoteApi: CharactersApi) : CharactersRemoteRepository {

    override fun getAllCharacters(): Single<Characters> {
        return Single.fromCallable { remoteApi.getAllCharacters().execute().body() }
    }
}