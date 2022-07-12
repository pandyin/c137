package com.c137.feature.search.api

import com.c137.domain.model.CharacterPresentation
import io.reactivex.rxjava3.core.Flowable

interface GetCharactersUseCase {

    fun execute(): Flowable<List<CharacterPresentation>>
}