package com.c137.presentation.api

import com.c137.presentation.model.CharacterPresentation
import io.reactivex.rxjava3.core.Flowable

interface GetCharactersUseCase {

    fun execute(): Flowable<List<CharacterPresentation>>
}