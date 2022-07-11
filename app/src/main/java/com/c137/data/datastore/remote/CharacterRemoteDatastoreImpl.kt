package com.c137.data.datastore.remote

import com.c137.data.model.Status
import com.c137.data.model.dto.CharacterDto
import com.c137.data.CharacterRemoteDatastore
import dagger.hilt.android.scopes.ViewModelScoped
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

@ViewModelScoped
class CharacterRemoteDatastoreImpl @Inject constructor(private val service: CharacterService) :
    CharacterRemoteDatastore {

    override fun getCharactersByStatus(page: Int, status: Status): Single<List<CharacterDto>> {
        return Single.fromCallable {
            service.getCharactersByStatus(page, status.name)
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