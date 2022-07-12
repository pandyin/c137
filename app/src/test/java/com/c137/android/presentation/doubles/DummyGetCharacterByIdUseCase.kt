package com.c137.android.presentation.doubles

import com.c137.presentation.api.GetCharacterByIdUseCase
import com.c137.domain.usecase.model.CharacterPresentation
import kotlinx.coroutines.flow.Flow

class DummyGetCharacterByIdUseCase : GetCharacterByIdUseCase {

    override fun execute(id: Int): Flow<CharacterPresentation> {
        TODO("Not yet implemented")
    }
}