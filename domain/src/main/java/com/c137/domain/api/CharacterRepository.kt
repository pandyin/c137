package com.c137.domain.api

import com.c137.domain.model.DomainCharacter
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {

    fun getCharactersByPage(page: Int): Flow<List<DomainCharacter>>

    fun getCharacterById(id: Int): Flow<DomainCharacter>
}