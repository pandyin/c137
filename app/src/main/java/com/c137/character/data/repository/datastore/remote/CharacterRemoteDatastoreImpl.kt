package com.c137.character.data.repository.datastore.remote

import com.c137.character.data.model.Character
import com.c137.character.data.model.Status
import com.c137.character.data.repository.datastore.remote.api.CharacterApi
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single

class CharacterRemoteDatastoreImpl(private val remoteApi: CharacterApi) :
    CharacterRemoteDatastore {

    override fun getCharactersByStatus(page: Int, status: Status): Single<List<Character>> {
        return Single.fromCallable {
            remoteApi.getCharactersByPage(page)
                .execute()
                .body()
                ?.characters ?: emptyList()
        }
    }

    override fun getCharacters(page: Int): Single<List<Character>> {
        return Single.fromCallable {
            remoteApi.getCharactersByPage(page)
                .execute()
                .body()
                ?.characters ?: emptyList()
        }
    }

    override fun getCharacterById(id: Int): Maybe<Character> {
        return Maybe.fromCallable {
            remoteApi.getCharacterById(id)
                .execute()
                .body()
        }
    }
}