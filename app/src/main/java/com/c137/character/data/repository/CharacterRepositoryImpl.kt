package com.c137.character.data.repository

import com.c137.character.data.model.Character
import com.c137.character.data.model.Status
import com.c137.character.data.repository.datastore.local.CharacterLocalDatastore
import com.c137.character.data.repository.datastore.remote.CharacterRemoteDatastore
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single

class CharacterRepositoryImpl(
    private val localDatastore: CharacterLocalDatastore,
    private val remoteDatastore: CharacterRemoteDatastore
) : CharacterRepository {

    override fun getCharactersByStatus(page: Int, status: Status): Single<List<Character>> {
        return remoteDatastore.getCharactersByStatus(page, status)
    }

    override fun getCharacters(page: Int): Single<List<Character>> {
        return remoteDatastore.getCharacters(page)
    }

    override fun getCharacterById(id: Int): Maybe<Character> {
        return remoteDatastore.getCharacterById(id)
    }
}