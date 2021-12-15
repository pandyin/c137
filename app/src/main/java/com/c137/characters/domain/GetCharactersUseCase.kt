package com.c137.characters.domain

import com.c137.characters.data.model.Character
import io.reactivex.rxjava3.core.Single

interface GetCharactersUseCase {

    fun execute(): Single<List<Character>>
}