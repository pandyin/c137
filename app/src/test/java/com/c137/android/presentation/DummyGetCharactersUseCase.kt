package com.c137.android.presentation

import com.c137.data.model.Character
import com.c137.domain.GetCharactersUseCase
import io.reactivex.rxjava3.core.Flowable

class DummyGetCharactersUseCase : GetCharactersUseCase {

    override fun execute(): Flowable<List<Character>> {
        TODO("Not yet implemented")
    }
}