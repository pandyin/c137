package com.c137.data.datastore.remote

import com.c137.data.datastore.remote.api.CharacterService
import com.c137.data.model.dto.CharacterDto
import com.c137.data.repository.api.CharacterRemoteDatastore
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class CharacterRemoteDatastoreImpl @Inject constructor(private val service: CharacterService) :
    CharacterRemoteDatastore {

    override suspend fun getCharacterById(id: Int): CharacterDto? {
        return service.getCharacterById(id)
    }
}