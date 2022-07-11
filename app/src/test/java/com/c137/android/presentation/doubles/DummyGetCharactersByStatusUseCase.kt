package com.c137.android.presentation.doubles

import com.c137.data.model.CharacterDataModel
import com.c137.data.model.Status
import com.c137.presentation.api.GetCharactersByStatusUseCase
import io.reactivex.rxjava3.core.Flowable

class DummyGetCharactersByStatusUseCase : GetCharactersByStatusUseCase {

    override fun execute(status: Status): Flowable<List<CharacterDataModel>> {
        TODO("Not yet implemented")
    }
}