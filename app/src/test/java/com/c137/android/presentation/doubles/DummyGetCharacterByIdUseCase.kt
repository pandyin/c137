package com.c137.android.presentation.doubles

import com.c137.data.model.CharacterDataModel
import com.c137.presentation.api.GetCharacterByIdUseCase
import kotlinx.coroutines.flow.Flow

class DummyGetCharacterByIdUseCase : GetCharacterByIdUseCase {

    override fun execute(id: Int): Flow<CharacterDataModel> {
        TODO("Not yet implemented")
    }
}