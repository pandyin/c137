package com.c137.domain

import com.c137.data.model.Character
import io.reactivex.rxjava3.core.Flowable

interface GetCharactersUseCase {

    fun execute(): Flowable<List<Character>>
}