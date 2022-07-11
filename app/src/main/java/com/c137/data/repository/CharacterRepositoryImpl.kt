package com.c137.data.repository

import com.c137.data.model.mapper.CharacterDataModelMapper
import com.c137.data.model.mapper.CharacterDtoMapper
import com.c137.data.model.Status
import com.c137.data.repository.api.CharacterLocalDatastore
import com.c137.data.repository.api.CharacterRemoteDatastore
import com.c137.domain.usecase.api.CharacterRepository
import com.c137.domain.usecase.model.CharacterDomainModel
import dagger.hilt.android.scopes.ViewModelScoped
import io.reactivex.rxjava3.core.Flowable
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@ViewModelScoped
class CharacterRepositoryImpl @Inject constructor(
    private val localDatastore: CharacterLocalDatastore,
    private val remoteDatastore: CharacterRemoteDatastore,
) : CharacterRepository {

    override fun getCharactersByStatus(status: Status): Flowable<List<CharacterDomainModel>> {
        return localDatastore.getCharactersByStatus(status)
            .map { it.map { data -> CharacterDataModelMapper().map(data) } }
            .mergeWith(remoteDatastore.getCharactersByStatus(1, status)
                .map { it.map { dto -> CharacterDtoMapper().map(dto) } }
                .flatMapCompletable { localDatastore.insertCharacters(it) })
    }

    override fun getCharacters(): Flowable<List<CharacterDomainModel>> {
        return localDatastore.getCharacters()
            .map { it.map { data -> CharacterDataModelMapper().map(data) } }
            .mergeWith(remoteDatastore.getCharacters(1)
                .map { it.map { dto -> CharacterDtoMapper().map(dto) } }
                .flatMapCompletable { localDatastore.insertCharacters(it) })
    }

    override fun getCharacterById(id: Int): Flow<CharacterDomainModel> = flow {
        val flow = localDatastore.getCharacterById(id)
        flow.firstOrNull()?.let { emit(CharacterDataModelMapper().map(it)) }
        remoteDatastore.getCharacterById(id)?.let {
            val dto = CharacterDtoMapper().map(it)
            localDatastore.insertCharacter(dto)
        }
        emitAll(flow.map { CharacterDataModelMapper().map(it) })
    }
}