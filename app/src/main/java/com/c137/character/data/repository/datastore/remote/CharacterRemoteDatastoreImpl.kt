package com.c137.character.data.repository.datastore.remote

import com.c137.character.data.model.Status
import com.c137.character.data.model.dto.CharacterDto
import com.c137.character.data.model.dto.LocationDto
import com.c137.character.data.repository.datastore.remote.api.CharacterApi
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single

class CharacterRemoteDatastoreImpl(private val remoteApi: CharacterApi) :
    CharacterRemoteDatastore {

    override fun getCharactersByStatus(page: Int, status: Status): Single<List<CharacterDto>> {
        return Single.fromCallable {
            remoteApi.getCharactersByStatus(page, status.name)
                .execute()
                .body()
                ?.results ?: emptyList()
        }
    }

    override fun getCharacters(page: Int): Single<List<CharacterDto>> {
        return Single.fromCallable {
            remoteApi.getCharactersByPage(page)
                .execute()
                .body()
                ?.results ?: emptyList()
        }
    }

    override fun getCharacterById(id: Int): Maybe<CharacterDto> {
        return Maybe.fromCallable {
            remoteApi.getCharacterById(id)
                .execute()
                .body()
        }
    }

    override fun getLocations(page: Int): Single<List<LocationDto>> {
        return Single.fromCallable {
            remoteApi.getLocation(page)
                .execute()
                .body()
                ?.results ?: emptyList()
        }
    }
}