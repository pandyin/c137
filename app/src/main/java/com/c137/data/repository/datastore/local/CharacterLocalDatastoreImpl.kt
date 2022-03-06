package com.c137.data.repository.datastore.local

import com.c137.data.model.Character
import com.c137.data.model.Status
import com.c137.data.repository.datastore.local.api.CharacterDao
import com.c137.di.ActivityScope
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import javax.inject.Inject

@ActivityScope
class CharacterLocalDatastoreImpl @Inject constructor(private val dao: CharacterDao) :
    CharacterLocalDatastore {

    override fun insertCharacter(character: Character): Completable {
        return dao.insertCharacter(character)
    }

    override fun insertCharacters(characters: List<Character>): Completable {
        return dao.insertCharacters(characters)
    }

    override fun getCharactersByStatus(status: Status): Flowable<List<Character>> {
        return dao.getCharactersByStatus(status)
    }

    override fun getCharacters(): Flowable<List<Character>> {
        return dao.getCharacters()
    }

    override fun getCharacterById(id: Int): Flowable<Character> {
        return dao.getCharacterById(id)
    }
}