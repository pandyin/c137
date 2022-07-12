package com.c137.presentation.api

import com.c137.domain.usecase.model.CharacterPresentation
import kotlinx.coroutines.flow.Flow

interface GetCharacterByIdUseCase {

    fun execute(id: Int): Flow<CharacterPresentation>
}