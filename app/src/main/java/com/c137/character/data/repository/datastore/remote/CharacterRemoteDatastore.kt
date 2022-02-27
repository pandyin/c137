package com.c137.character.data.repository.datastore.remote

import com.c137.character.data.model.Character
import com.c137.character.data.model.Status
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single

interface CharacterRemoteDatastore {

    fun getCharactersByStatus(page: Int, status: Status): Single<List<Character>>

    fun getCharacters(page: Int): Single<List<Character>>

    fun getCharacterById(id: Int): Maybe<Character>
}