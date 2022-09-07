package com.c137.data.repository.api

import com.c137.data.model.dto.CharacterDto

interface CharacterRemoteDataSource {

    suspend fun getCharacterById(id: Int): CharacterDto
}