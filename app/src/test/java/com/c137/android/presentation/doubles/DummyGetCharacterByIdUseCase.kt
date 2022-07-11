package com.c137.android.presentation.doubles

import com.c137.data.model.Character
import com.c137.presentation.api.GetCharacterByIdUseCase
import kotlinx.coroutines.flow.Flow

class DummyGetCharacterByIdUseCase : GetCharacterByIdUseCase {

    override fun execute(id: Int): Flow<Character> {
        TODO("Not yet implemented")
    }
}