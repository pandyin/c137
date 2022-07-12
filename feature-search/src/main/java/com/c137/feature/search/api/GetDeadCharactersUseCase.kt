package com.c137.feature.search.api

import com.c137.model.CharacterPresentation
import io.reactivex.rxjava3.core.Flowable

interface GetDeadCharactersUseCase {

    fun execute(): Flowable<List<CharacterPresentation>>
}