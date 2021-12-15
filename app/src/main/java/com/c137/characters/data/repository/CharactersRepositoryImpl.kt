package com.c137.characters.data.repository

import com.c137.characters.data.model.Character
import com.c137.characters.data.repository.datastore.remote.CharactersRemoteRepository
import io.reactivex.rxjava3.core.Single

class CharactersRepositoryImpl(private val remoteRepository: CharactersRemoteRepository) : CharactersRepository {

    override fun getCharacters(): Single<List<Character>> {
        return remoteRepository.getCharacters()
    }
}