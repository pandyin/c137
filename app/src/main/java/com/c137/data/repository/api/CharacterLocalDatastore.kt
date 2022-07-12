package com.c137.data.repository.api

import com.c137.data.model.CharacterData
import com.c137.common.model.CharacterStatus
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import kotlinx.coroutines.flow.Flow

interface CharacterLocalDatastore {

    fun getCharactersByStatus(status: CharacterStatus): Flowable<List<CharacterData>>

    fun getCharacters(): Flowable<List<CharacterData>>

    fun insertCharacters(characters: List<CharacterData>): Completable

    fun getCharacterById(id: Int): Flow<CharacterData>

    suspend fun insertCharacter(character: CharacterData)
}