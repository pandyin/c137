package com.c137.presentation.api

import com.c137.data.model.Status
import com.c137.presentation.model.CharacterPresentationModel
import io.reactivex.rxjava3.core.Flowable

interface GetCharactersByStatusUseCase {

    fun execute(status: Status): Flowable<List<CharacterPresentationModel>>
}