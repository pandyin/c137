package com.c137.characters.data.repository.datastore.local

import com.c137.characters.data.model.Character
import com.c137.characters.data.repository.datastore.local.api.CharactersDao
import io.reactivex.rxjava3.core.Completable

class CharactersLocalDatastoreImpl(private val charactersDao: CharactersDao) : CharactersLocalDatastore {

    override fun insertCharacters(characters: List<Character>): Completable {
        return charactersDao.insertCharacters(characters)
    }
}