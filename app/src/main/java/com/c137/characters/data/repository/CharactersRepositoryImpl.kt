package com.c137.characters.data.repository

import com.c137.characters.data.model.Characters
import com.c137.characters.data.repository.datastore.remote.CharactersRemoteRepository
import io.reactivex.rxjava3.core.Single

class CharactersRepositoryImpl(private val remoteRepository: CharactersRemoteRepository) : CharactersRepository {

    override fun getAllCharacters(): Single<Characters> {
        return remoteRepository.getAllCharacters()
    }
}