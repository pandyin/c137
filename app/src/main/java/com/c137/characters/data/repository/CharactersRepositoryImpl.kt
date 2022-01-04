package com.c137.characters.data.repository

import com.c137.characters.data.model.Character
import com.c137.characters.data.repository.datastore.remote.CharactersRemoteDatastore
import com.c137.characters.data.repository.datastore.remote.CharactersRemoteDatastoreImpl
import io.reactivex.rxjava3.core.Single
import org.jetbrains.annotations.TestOnly

class CharactersRepositoryImpl(private val remoteRepository: CharactersRemoteDatastore) : CharactersRepository {

    @TestOnly
    constructor() : this(CharactersRemoteDatastoreImpl())

    override fun getCharacters(): Single<List<Character>> {
        return remoteRepository.getCharacters()
    }
}