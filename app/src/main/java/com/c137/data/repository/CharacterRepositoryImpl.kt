package com.c137.data.repository

import com.c137.data.model.Character
import com.c137.data.model.Status
import com.c137.data.model.dto.CharacterDtoMapper
import com.c137.data.repository.datastore.local.CharacterLocalDatastore
import com.c137.data.repository.datastore.remote.CharacterRemoteDatastore
import dagger.hilt.android.scopes.ViewModelScoped
import io.reactivex.rxjava3.core.Flowable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@ViewModelScoped
class CharacterRepositoryImpl @Inject constructor(
    private val localDatastore: CharacterLocalDatastore,
    private val remoteDatastore: CharacterRemoteDatastore,
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

    override fun getCharacterById(id: Int): Flow<Character> {
        return localDatastore.getCharacterById(id)
            .onStart {
                remoteDatastore.getCharacterById(id)?.let {
                    val character = CharacterDtoMapper().map(it)
                    localDatastore.insertCharacter(character)
                }
            }
    }
}