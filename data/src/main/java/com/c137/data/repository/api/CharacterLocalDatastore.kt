package com.c137.data.repository.api

import com.c137.data.model.DataCharacter
import kotlinx.coroutines.flow.Flow

interface CharacterLocalDatastore {

    fun getCharacterById(id: Int): Flow<DataCharacter>

    suspend fun insertCharacter(character: DataCharacter)
}