package com.c137.character.data.model.dto

import com.c137.character.data.model.BaseEntity

interface DtoMapper<DTO, ENTITY : BaseEntity> {

    fun map(dto: DTO): ENTITY
}