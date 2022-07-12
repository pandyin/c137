package com.c137.android.presentation.doubles

import com.c137.presentation.api.GetAliveCharactersUseCase
import com.c137.presentation.model.CharacterPresentation
import io.reactivex.rxjava3.core.Flowable

class DummyGetAliveCharactersUseCase : GetAliveCharactersUseCase {

    override fun execute(): Flowable<List<CharacterPresentation>> {
        TODO("Not yet implemented")
    }
}