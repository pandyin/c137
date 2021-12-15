package com.c137.characters.data.repository

import com.c137.characters.data.model.Characters
import io.reactivex.rxjava3.core.Single

interface CharactersRepository {

    fun getAllCharacters(): Single<Characters>
}