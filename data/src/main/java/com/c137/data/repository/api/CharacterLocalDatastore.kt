package com.c137.data.repository.api

import com.c137.data.model.DataCharacter
import io.reactivex.rxjava3.core.Completable
import kotlinx.coroutines.flow.Flow

interface CharacterLocalDatastore {

    fun insertCharacters(characters: List<DataCharacter>): Completable

    fun getCharacterById(id: Int): Flow<DataCharacter>

    suspend fun insertCharacter(character: DataCharacter)
}