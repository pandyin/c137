package com.c137.android.presentation.doubles

import com.c137.presentation.api.GetDeadCharactersUseCase
import com.c137.domain.usecase.model.CharacterPresentation
import io.reactivex.rxjava3.core.Flowable

class DummyGetDeadCharactersUseCase : GetDeadCharactersUseCase {

    override fun execute(): Flowable<List<CharacterPresentation>> {
        TODO("Not yet implemented")
    }
}