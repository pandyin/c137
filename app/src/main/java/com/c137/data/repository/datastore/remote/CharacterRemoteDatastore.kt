package com.c137.data.repository.datastore.remote

import com.c137.data.model.Status
import com.c137.data.model.dto.CharacterDto
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single

interface CharacterRemoteDatastore {

    fun getCharactersByStatus(page: Int, status: Status): Single<List<CharacterDto>>

    fun getCharacters(page: Int): Single<List<CharacterDto>>

    fun getCharacterById(id: Int): Maybe<CharacterDto>
}