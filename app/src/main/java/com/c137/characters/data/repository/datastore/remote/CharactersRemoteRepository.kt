package com.c137.characters.data.repository.datastore.remote

import com.c137.characters.data.model.Character
import io.reactivex.rxjava3.core.Single

interface CharactersRemoteRepository {

    fun getCharacters(): Single<List<Character>>
}