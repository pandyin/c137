package com.c137.data

import com.c137.data.model.Character
import com.c137.data.model.Status
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import kotlinx.coroutines.flow.Flow

interface CharacterLocalDatastore {

    fun getCharactersByStatus(status: Status): Flowable<List<Character>>

    fun getCharacters(): Flowable<List<Character>>

    fun insertCharacters(characters: List<Character>): Completable

    fun getCharacterById(id: Int): Flow<Character>

    suspend fun insertCharacter(character: Character)
}