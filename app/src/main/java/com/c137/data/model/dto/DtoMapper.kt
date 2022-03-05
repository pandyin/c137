package com.c137.data.model.dto

import com.c137.data.model.BaseEntity

interface DtoMapper<DTO, ENTITY : BaseEntity> {

    fun map(dto: DTO): ENTITY
}