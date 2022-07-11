package com.c137.data

import com.c137.data.model.Status
import com.c137.data.model.dto.CharacterDto
import io.reactivex.rxjava3.core.Single

interface CharacterRemoteDatastore {

    fun getCharactersByStatus(page: Int, status: Status): Single<List<CharacterDto>>

    fun getCharacters(page: Int): Single<List<CharacterDto>>

    suspend fun getCharacterById(id: Int): CharacterDto?
}