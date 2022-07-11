package com.c137.data.repository.api

import com.c137.data.model.CharacterDataModel
import com.c137.data.model.Status
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import kotlinx.coroutines.flow.Flow

interface CharacterLocalDatastore {

    fun getCharactersByStatus(status: Status): Flowable<List<CharacterDataModel>>

    fun getCharacters(): Flowable<List<CharacterDataModel>>

    fun insertCharacters(characters: List<CharacterDataModel>): Completable

    fun getCharacterById(id: Int): Flow<CharacterDataModel>

    suspend fun insertCharacter(character: CharacterDataModel)
}