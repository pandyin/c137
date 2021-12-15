package com.c137.characters.data.repository.datastore.remote

import com.c137.characters.data.model.Characters
import io.reactivex.rxjava3.core.Single

interface CharactersRemoteRepository {

    fun getAllCharacters(): Single<Characters>
}