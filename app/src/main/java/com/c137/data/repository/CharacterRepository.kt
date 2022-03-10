package com.c137.data.repository

import com.c137.data.model.Character
import com.c137.data.model.Status
import io.reactivex.rxjava3.core.Flowable
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {

    fun getCharactersByStatus(page: Int, status: Status): Flowable<List<Character>>

    fun getCharacters(page: Int): Flowable<List<Character>>

    fun getCharacterById(id: Int): Flow<Character>
}