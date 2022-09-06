package com.c137.data.repository.api

import com.c137.data.model.dto.CharacterDto
import com.c137.data.model.dto.ResultsDto

interface CharacterRemoteDatastore {

    suspend fun getCharacterByPage(page: Int): ResultsDto<CharacterDto>

    suspend fun getCharacterById(id: Int): CharacterDto
}