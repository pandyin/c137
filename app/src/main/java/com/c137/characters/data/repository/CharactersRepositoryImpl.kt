package com.c137.characters.data.repository

import com.c137.characters.data.model.Character
import com.c137.characters.data.model.Status
import com.c137.characters.data.repository.datastore.local.CharactersLocalDatastore
import com.c137.characters.data.repository.datastore.remote.CharactersRemoteDatastore
import io.reactivex.rxjava3.core.Single

class CharactersRepositoryImpl(
    private val localDatastore: CharactersLocalDatastore,
    private val remoteDatastore: CharactersRemoteDatastore
) : CharactersRepository {

    override fun getCharactersByStatus(status: Status): Single<List<Character>> {
        return remoteDatastore.getCharactersByStatus(status)
    }

    override fun getCharacters(): Single<List<Character>> {
        return remoteDatastore.getCharacters()
    }
}