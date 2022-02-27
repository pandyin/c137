package com.c137.character.data.repository.datastore.local

import com.c137.character.data.model.Character
import io.reactivex.rxjava3.core.Completable

interface CharacterLocalDatastore {

    fun insertCharacters(characters: List<Character>): Completable
}