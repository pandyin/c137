package com.c137.data.repository

import com.c137.data.repository.api.CharacterLocalDatastore
import com.c137.data.repository.api.CharacterRemoteDatastore
import com.c137.domain.api.CharacterRepository
import com.c137.domain.model.DomainCharacter
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class CharacterRepositoryImpl @Inject constructor(
    private val localDatastore: CharacterLocalDatastore,
    private val remoteDatastore: CharacterRemoteDatastore,
) : CharacterRepository {

    override fun getCharactersByPage(page: Int): Flow<List<DomainCharacter>> {
        TODO("Not yet implemented")
    }

    override fun getCharacterById(id: Int): Flow<DomainCharacter> {
        TODO("Not yet implemented")
    }
}