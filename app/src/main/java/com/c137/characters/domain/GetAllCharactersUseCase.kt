package com.c137.characters.domain

import com.c137.characters.data.model.Characters
import io.reactivex.rxjava3.core.Single

interface GetAllCharactersUseCase {

    fun execute(): Single<Characters>
}