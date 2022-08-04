package com.c137.data.repository.api

import com.c137.data.model.DataCharacter
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import kotlinx.coroutines.flow.Flow

interface CharacterLocalDatastore {

    fun getAliveCharacters(): Flowable<List<DataCharacter>>

    fun getDeadCharacters(): Flowable<List<DataCharacter>>

    fun getCharacters(): Flowable<List<DataCharacter>>

    fun insertCharacters(characters: List<DataCharacter>): Completable

    fun getCharacterById(id: Int): Flow<DataCharacter>

    suspend fun insertCharacter(character: DataCharacter)
}