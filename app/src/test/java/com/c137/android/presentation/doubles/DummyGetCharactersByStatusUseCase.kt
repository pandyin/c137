package com.c137.android.presentation.doubles

import com.c137.data.model.CharacterData
import com.c137.data.model.CharacterStatus
import com.c137.presentation.api.GetAliveCharactersUseCase
import io.reactivex.rxjava3.core.Flowable

class DummyGetCharactersByStatusUseCase : GetAliveCharactersUseCase {

    override fun execute(status: CharacterStatus): Flowable<List<CharacterData>> {
        TODO("Not yet implemented")
    }
}