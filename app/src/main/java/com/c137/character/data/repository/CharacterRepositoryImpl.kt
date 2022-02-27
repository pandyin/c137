package com.c137.character.data.repository

import com.c137.character.data.model.Character
import com.c137.character.data.model.LocationWithResidents
import com.c137.character.data.model.Status
import com.c137.character.data.model.dto.CharacterDtoMapper
import com.c137.character.data.model.dto.LocationDtoMapper
import com.c137.character.data.repository.datastore.local.CharacterLocalDatastore
import com.c137.character.data.repository.datastore.remote.CharacterRemoteDatastore
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

    override fun getLocations(page: Int): Flowable<List<LocationWithResidents>> {
        return localDatastore.getLocations()
            .mergeWith(remoteDatastore.getLocations(page)
                .map { it.map { dto -> LocationDtoMapper().map(dto) } }
                .flatMapCompletable { localDatastore.insertLocations(it) })
    }
}