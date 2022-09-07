package com.c137.data.repository

import com.c137.data.repository.api.CharacterLocalDataSource
import com.c137.data.repository.api.CharacterRemoteDataSource
import com.c137.domain.api.CharacterRepository
import com.c137.domain.model.DomainCharacter
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class CharacterRepositoryImpl @Inject constructor(
    private val localDataSource: CharacterLocalDataSource,
    private val remoteDataSource: CharacterRemoteDataSource,
) : CharacterRepository {

    override fun getCharacterById(id: Int): Flow<DomainCharacter> {
        TODO("Not yet implemented")
    }
}