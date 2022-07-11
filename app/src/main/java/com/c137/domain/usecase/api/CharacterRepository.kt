package com.c137.domain.usecase.api

import com.c137.data.model.Character
import com.c137.data.model.Status
import io.reactivex.rxjava3.core.Flowable
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {

    fun getCharactersByStatus(status: Status): Flowable<List<Character>>

    fun getCharacters(): Flowable<List<Character>>

    fun getCharacterById(id: Int): Flow<Character>
}