package com.c137.android.presentation

import com.c137.data.model.Character
import com.c137.domain.GetCharacterByIdUseCase
import kotlinx.coroutines.flow.Flow

class DummyGetCharacterByIdUseCase : GetCharacterByIdUseCase {

    override fun execute(id: Int): Flow<Character> {
        TODO("Not yet implemented")
    }
}