package com.c137.domain

import com.c137.data.model.Character
import kotlinx.coroutines.flow.Flow

interface GetCharacterByIdUseCase {

    fun execute(id: Int): Flow<Character>
}