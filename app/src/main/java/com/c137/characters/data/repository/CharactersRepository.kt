package com.c137.characters.data.repository

import com.c137.characters.data.model.Character
import io.reactivex.rxjava3.core.Single

interface CharactersRepository {

    fun getCharacters(): Single<List<Character>>
}