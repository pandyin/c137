package com.c137.domain

import com.c137.data.model.Character
import com.c137.data.model.Status
import io.reactivex.rxjava3.core.Flowable

interface GetCharactersByStatusUseCase {

    fun execute(status: Status): Flowable<List<Character>>
}