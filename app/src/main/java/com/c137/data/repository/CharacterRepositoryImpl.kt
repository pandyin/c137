package com.c137.data.repository

import com.c137.data.model.Character
import com.c137.data.model.Status
import com.c137.data.model.dto.CharacterDtoMapper
import com.c137.data.repository.datastore.local.CharacterLocalDatastore
import com.c137.data.repository.datastore.remote.CharacterRemoteDatastore
import io.reactivex.rxjava3.core.Flowable

class CharacterRepositoryImpl(
    private val localDatastore: CharacterLocalDatastore,
    private val remoteDatastore: CharacterRemoteDatastore
) : CharacterRepository {

    override fun getCharactersByStatus(page: Int, status: Status): Flowable<List<Character>> {
        return localDatastore.getCharactersByStatus(status)
            .mergeWith(remoteDatastore.getCharactersByStatus(page, status)
                .map { it.map { dto -> CharacterDtoMapper().map(dto) } }
                .flatMapCompletable { localDatastore.insertCharacters(it) })
    }

    override fun getCharacters(page: Int): Flowable<List<Character>> {
        return localDatastore.getCharacters()
            .mergeWith(remoteDatastore.getCharacters(page)
                .map { it.map { dto -> CharacterDtoMapper().map(dto) } }
                .flatMapCompletable { localDatastore.insertCharacters(it) })
    }

    override fun getCharacterById(id: Int): Flowable<Character> {
        return localDatastore.getCharacterById(id)
            .mergeWith(remoteDatastore.getCharacterById(id)
                .map { CharacterDtoMapper().map(it) }
                .flatMapCompletable { localDatastore.insertCharacter(it) })
    }
}