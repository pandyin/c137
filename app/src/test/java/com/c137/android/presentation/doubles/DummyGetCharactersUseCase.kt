package com.c137.android.presentation.doubles

import com.c137.data.model.CharacterData
import com.c137.presentation.api.GetCharactersUseCase
import io.reactivex.rxjava3.core.Flowable

class DummyGetCharactersUseCase : GetCharactersUseCase {

    override fun execute(): Flowable<List<CharacterData>> {
        TODO("Not yet implemented")
    }
}