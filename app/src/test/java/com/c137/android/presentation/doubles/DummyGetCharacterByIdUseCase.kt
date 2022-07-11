package com.c137.android.presentation.doubles

import com.c137.data.model.CharacterData
import com.c137.presentation.api.GetCharacterByIdUseCase
import kotlinx.coroutines.flow.Flow

class DummyGetCharacterByIdUseCase : GetCharacterByIdUseCase {

    override fun execute(id: Int): Flow<CharacterData> {
        TODO("Not yet implemented")
    }
}