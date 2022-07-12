package com.c137.android.presentation.doubles

import com.c137.data.model.CharacterData
import com.c137.common.model.CharacterStatus
import com.c137.presentation.api.GetCharactersByStatusUseCase
import io.reactivex.rxjava3.core.Flowable

class DummyGetCharactersByStatusUseCase : GetCharactersByStatusUseCase {

    override fun execute(status: CharacterStatus): Flowable<List<CharacterData>> {
        TODO("Not yet implemented")
    }
}