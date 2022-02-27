package com.c137.character.domain

import com.c137.character.data.model.Character
import io.reactivex.rxjava3.core.Flowable

interface GetCharacterByIdUseCase {

    fun execute(id: Int): Flowable<Character>
}