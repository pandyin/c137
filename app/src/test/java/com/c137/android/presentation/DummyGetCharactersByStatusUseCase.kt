package com.c137.android.presentation

import com.c137.data.model.Character
import com.c137.data.model.Status
import com.c137.domain.GetCharactersByStatusUseCase
import io.reactivex.rxjava3.core.Flowable

class DummyGetCharactersByStatusUseCase : GetCharactersByStatusUseCase {

    override fun execute(status: Status): Flowable<List<Character>> {
        TODO("Not yet implemented")
    }
}