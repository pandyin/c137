package com.c137.feature.search.api

import com.c137.model.CharacterPresentation
import kotlinx.coroutines.flow.Flow

interface GetCharacterByIdUseCase {

    fun execute(id: Int): Flow<CharacterPresentation>
}