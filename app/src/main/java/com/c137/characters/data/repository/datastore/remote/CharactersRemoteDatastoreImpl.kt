package com.c137.characters.data.repository.datastore.remote

import com.c137.characters.common.ResultConverter
import com.c137.characters.data.model.Character
import com.c137.characters.data.model.Status
import com.c137.characters.data.repository.datastore.remote.api.CharactersApi
import io.reactivex.rxjava3.core.Single


class CharactersRemoteDatastoreImpl(private val remoteApi: CharactersApi) : CharactersRemoteDatastore {

    override fun getCharactersByStatus(status: Status): Single<List<Character>> {
        return Single.fromCallable {
            remoteApi.getCharacters(1)
                .execute()
                .body()
        }.map {
            ResultConverter.convert(it)
        }
    }

    override fun getCharacters(): Single<List<Character>> {
        return Single.fromCallable {
            remoteApi.getCharacters(1)
                .execute()
                .body()
        }.map {
            ResultConverter.convert(it)
        }
    }
}