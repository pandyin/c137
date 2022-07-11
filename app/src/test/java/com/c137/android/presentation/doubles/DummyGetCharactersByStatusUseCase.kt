package com.c137.android.presentation.doubles

import com.c137.data.model.CharacterData
import com.c137.data.model.Status
import com.c137.presentation.api.GetCharactersByStatusUseCase
import io.reactivex.rxjava3.core.Flowable

class DummyGetCharactersByStatusUseCase : GetCharactersByStatusUseCase {

    override fun execute(status: Status): Flowable<List<CharacterData>> {
        TODO("Not yet implemented")
    }
}