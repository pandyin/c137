package com.c137.data.repository.api

import com.c137.data.model.dto.CharacterDto
import io.reactivex.rxjava3.core.Single

interface CharacterRemoteDatastore {

    fun getAliveCharacters(page: Int): Single<List<CharacterDto>>

    fun getDeadCharacters(page: Int): Single<List<CharacterDto>>

    fun getCharacters(page: Int): Single<List<CharacterDto>>

    suspend fun getCharacterById(id: Int): CharacterDto?
}