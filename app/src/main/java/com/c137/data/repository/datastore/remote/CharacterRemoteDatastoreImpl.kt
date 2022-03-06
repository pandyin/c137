package com.c137.data.repository.datastore.remote

import com.c137.data.model.Status
import com.c137.data.model.dto.CharacterDto
import com.c137.data.repository.datastore.remote.api.CharacterService
import com.c137.di.ActivityScope
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

@ActivityScope
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

    override fun getCharacterById(id: Int): Maybe<CharacterDto> {
        return Maybe.fromCallable {
            service.getCharacterById(id)
                .execute()
                .body()
        }
    }
}