package com.c137.android.presentation.doubles

import com.c137.data.model.CharacterDataModel
import com.c137.presentation.api.GetCharactersUseCase
import io.reactivex.rxjava3.core.Flowable

class DummyGetCharactersUseCase : GetCharactersUseCase {

    override fun execute(): Flowable<List<CharacterDataModel>> {
        TODO("Not yet implemented")
    }
}