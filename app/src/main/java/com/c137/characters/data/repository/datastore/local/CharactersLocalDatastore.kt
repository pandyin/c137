package com.c137.characters.data.repository.datastore.local

import com.c137.characters.data.model.Character
import io.reactivex.rxjava3.core.Completable

interface CharactersLocalDatastore {

    fun insertCharacters(characters: List<Character>): Completable
}