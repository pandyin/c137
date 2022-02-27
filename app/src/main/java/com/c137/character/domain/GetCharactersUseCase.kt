package com.c137.character.domain

import com.c137.character.data.model.Character
import io.reactivex.rxjava3.core.Flowable

interface GetCharactersUseCase {

    fun execute(page: Int): Flowable<List<Character>>
}