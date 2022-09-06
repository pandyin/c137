package com.c137.domain.api

import com.c137.domain.model.DomainCharacter
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {

    fun getCharacterById(id: Int): Flow<DomainCharacter>
}