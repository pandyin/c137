package com.c137.data.repository

import com.c137.data.repository.api.CharacterLocalDatastore
import com.c137.data.repository.api.CharacterRemoteDatastore
import com.c137.data.model.Character
import com.c137.data.model.Status
import com.c137.data.model.dto.CharacterDtoMapper
import com.c137.domain.usecase.api.CharacterRepository
import dagger.hilt.android.scopes.ViewModelScoped
import io.reactivex.rxjava3.core.Flowable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@ViewModelScoped
class CharacterRepositoryImpl @Inject constructor(
    private val localDatastore: CharacterLocalDatastore,
    private val remoteDatastore: CharacterRemoteDatastore,
) : CharacterRepository {

    override fun getCharactersByStatus(status: Status): Flowable<List<Character>> {
        return localDatastore.getCharactersByStatus(status)
            .mergeWith(remoteDatastore.getCharactersByStatus(1, status)
                .map { it.map { dto -> CharacterDtoMapper().map(dto) } }
                .flatMapCompletable { localDatastore.insertCharacters(it) })
    }

    override fun getCharacters(): Flowable<List<Character>> {
        return localDatastore.getCharacters()
            .mergeWith(remoteDatastore.getCharacters(1)
                .map { it.map { dto -> CharacterDtoMapper().map(dto) } }
                .flatMapCompletable { localDatastore.insertCharacters(it) })
    }

    override fun getCharacterById(id: Int): Flow<Character> = flow {
        val flow = localDatastore.getCharacterById(id)
        flow.firstOrNull()?.let { emit(it) }
        remoteDatastore.getCharacterById(id)?.let {
            val dto = CharacterDtoMapper().map(it)
            localDatastore.insertCharacter(dto)
        }
        emitAll(flow)
    }
}