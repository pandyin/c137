package com.c137.character.domain

import com.c137.character.data.model.Character
import com.c137.character.data.model.Status
import io.reactivex.rxjava3.core.Flowable

interface GetCharactersByStatusUseCase {

    fun execute(page: Int, status: Status): Flowable<List<Character>>
}