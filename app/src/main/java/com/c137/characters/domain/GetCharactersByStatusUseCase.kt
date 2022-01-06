package com.c137.characters.domain

import com.c137.characters.data.model.Character
import com.c137.characters.data.model.Status
import io.reactivex.rxjava3.core.Single

interface GetCharactersByStatusUseCase {

    fun execute(status: Status): Single<List<Character>>
}