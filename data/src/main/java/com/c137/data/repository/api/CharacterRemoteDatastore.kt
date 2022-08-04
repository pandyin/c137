package com.c137.data.repository.api

import com.c137.data.model.dto.CharacterDto

interface CharacterRemoteDatastore {

    suspend fun getCharacterById(id: Int): CharacterDto?
}