package com.c137.data.repository

import com.c137.data.model.mapper.DataCharacterMapper
import com.c137.data.model.mapper.CharacterDtoMapper
import com.c137.data.repository.api.CharacterLocalDatastore
import com.c137.data.repository.api.CharacterRemoteDatastore
import com.c137.domain.api.CharacterRepository
import com.c137.domain.model.DomainCharacter
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@ViewModelScoped
class CharacterRepositoryImpl @Inject constructor(
    private val localDatastore: CharacterLocalDatastore,
    private val remoteDatastore: CharacterRemoteDatastore,
) : CharacterRepository {

    override fun getCharacterById(id: Int): Flow<DomainCharacter> = flow {
        val flow = localDatastore.getCharacterById(id)
        flow.firstOrNull()?.let { emit(DataCharacterMapper().map(it)) }
        remoteDatastore.getCharacterById(id)?.let {
            val dto = CharacterDtoMapper().map(it)
            localDatastore.insertCharacter(dto)
        }
        emitAll(flow.map { DataCharacterMapper().map(it) })
    }
}