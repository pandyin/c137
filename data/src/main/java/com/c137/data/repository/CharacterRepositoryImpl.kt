package com.c137.data.repository

import com.c137.domain.api.CharacterRepository
import com.c137.data.model.mapper.CharacterDataMapper
import com.c137.data.model.mapper.CharacterDtoMapper
import com.c137.data.repository.api.CharacterLocalDatastore
import com.c137.data.repository.api.CharacterRemoteDatastore
import com.c137.domain.model.CharacterDomain
import dagger.hilt.android.scopes.ViewModelScoped
import io.reactivex.rxjava3.core.Flowable
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@ViewModelScoped
class CharacterRepositoryImpl @Inject constructor(
    private val localDatastore: CharacterLocalDatastore,
    private val remoteDatastore: CharacterRemoteDatastore,
) : CharacterRepository {

    override fun getAliveCharacters(): Flowable<List<CharacterDomain>> {
        return localDatastore.getAliveCharacters()
            .map { it.map { data -> CharacterDataMapper().map(data) } }
            .mergeWith(remoteDatastore.getAliveCharacters(1)
                .map { it.map { dto -> CharacterDtoMapper().map(dto) } }
                .flatMapCompletable { localDatastore.insertCharacters(it) })
    }


    override fun getDeadCharacters(): Flowable<List<CharacterDomain>> {
        return localDatastore.getDeadCharacters()
            .map { it.map { data -> CharacterDataMapper().map(data) } }
            .mergeWith(remoteDatastore.getDeadCharacters(1)
                .map { it.map { dto -> CharacterDtoMapper().map(dto) } }
                .flatMapCompletable { localDatastore.insertCharacters(it) })
    }

    override fun getCharacters(): Flowable<List<CharacterDomain>> {
        return localDatastore.getCharacters()
            .map { it.map { data -> CharacterDataMapper().map(data) } }
            .mergeWith(remoteDatastore.getCharacters(1)
                .map { it.map { dto -> CharacterDtoMapper().map(dto) } }
                .flatMapCompletable { localDatastore.insertCharacters(it) })
    }

    override fun getCharacterById(id: Int): Flow<CharacterDomain> = flow {
        val flow = localDatastore.getCharacterById(id)
        flow.firstOrNull()?.let { emit(CharacterDataMapper().map(it)) }
        remoteDatastore.getCharacterById(id)?.let {
            val dto = CharacterDtoMapper().map(it)
            localDatastore.insertCharacter(dto)
        }
        emitAll(flow.map { CharacterDataMapper().map(it) })
    }
}