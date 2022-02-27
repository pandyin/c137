package com.c137.character.data.repository.datastore.local

import com.c137.character.data.model.Character
import com.c137.character.data.repository.datastore.local.api.CharacterDao
import io.reactivex.rxjava3.core.Completable

class CharacterLocalDatastoreImpl(private val characterDao: CharacterDao) : CharacterLocalDatastore {

    override fun insertCharacters(characters: List<Character>): Completable {
        return characterDao.insertCharacters(characters)
    }
}