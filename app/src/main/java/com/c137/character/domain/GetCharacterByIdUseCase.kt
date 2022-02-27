package com.c137.character.domain

import com.c137.character.data.model.Character
import io.reactivex.rxjava3.core.Maybe

interface GetCharacterByIdUseCase {

    fun execute(id: Int): Maybe<Character>
}