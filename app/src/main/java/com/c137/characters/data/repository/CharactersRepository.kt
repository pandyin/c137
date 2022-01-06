package com.c137.characters.data.repository

import com.c137.characters.data.model.Character
import com.c137.characters.data.model.Status
import io.reactivex.rxjava3.core.Single

interface CharactersRepository {

    fun getCharactersByStatus(status: Status): Single<List<Character>>

    fun getCharacters(): Single<List<Character>>
}