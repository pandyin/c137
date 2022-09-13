package com.c137.data.model.mapper.dto

import com.c137.data.model.BaseDataModel

interface DtoMapper<DTO, ENTITY : BaseDataModel> {

    fun map(dto: DTO): ENTITY
}