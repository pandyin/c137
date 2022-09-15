package com.c137.data.datasource.remote

import com.c137.data.datasource.remote.api.CharacterService
import com.c137.data.repository.api.CharacterRemoteDataSource
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class CharacterRemoteDataSourceImpl @Inject constructor(private val service: CharacterService) :
    CharacterRemoteDataSource {

    override suspend fun getCharacterById(id: Int) = service.getCharacterById(id)
}