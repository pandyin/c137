package com.c137.characters.data.repository.datastore.local

import com.c137.characters.data.model.Character
import com.c137.characters.data.repository.datastore.local.api.CharactersDao
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import org.jetbrains.annotations.TestOnly

class CharactersLocalDatastoreImpl(private val charactersDao: CharactersDao) : CharactersLocalDatastore {

    @TestOnly
    constructor() : this(object : CharactersDao {
        override fun insertCharacters(characters: List<Character>): Completable {
            TODO("Not yet implemented")
        }

        override fun getCharacters(): Flowable<List<Character>> {
            TODO("Not yet implemented")
        }
    })
}