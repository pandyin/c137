package com.c137.data.datastore.remote

import com.c137.data.datastore.remote.api.CharacterService
import com.c137.data.model.CharacterStatus
import com.c137.data.model.dto.CharacterDto
import com.c137.data.repository.api.CharacterRemoteDatastore
import dagger.hilt.android.scopes.ViewModelScoped
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

@ViewModelScoped
class CharacterRemoteDatastoreImpl @Inject constructor(private val service: CharacterService) :
    CharacterRemoteDatastore {

    override fun getAliveCharacters(page: Int): Single<List<CharacterDto>> {
        return Single.fromCallable {
            service.getCharactersByStatus(page, CharacterStatus.Alive.name)
                .execute()
                .body()
                ?.results ?: emptyList()
        }
    }

    override fun getDeadCharacters(page: Int): Single<List<CharacterDto>> {
        return Single.fromCallable {
            service.getCharactersByStatus(page, CharacterStatus.Dead.name)
                .execute()
                .body()
                ?.results ?: emptyList()
        }
    }

    override fun getCharacters(page: Int): Single<List<CharacterDto>> {
        return Single.fromCallable {
            service.getCharactersByPage(page)
                .execute()
                .body()
                ?.results ?: emptyList()
        }
    }

    override suspend fun getCharacterById(id: Int): CharacterDto? {
        return service.getCharacterById(id)
    }
}