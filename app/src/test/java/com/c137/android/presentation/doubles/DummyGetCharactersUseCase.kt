package com.c137.android.presentation.doubles

import com.c137.data.model.Character
import com.c137.presentation.GetCharactersUseCase
import io.reactivex.rxjava3.core.Flowable

class DummyGetCharactersUseCase : GetCharactersUseCase {

    override fun execute(): Flowable<List<Character>> {
        TODO("Not yet implemented")
    }
}