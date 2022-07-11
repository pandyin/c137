package com.c137.data.model.mapper

import com.c137.data.model.CharacterDataModel
import com.c137.data.model.Status
import com.c137.data.model.dto.CharacterDto

class CharacterDtoMapper : DtoMapper<CharacterDto, CharacterDataModel> {

    override fun map(dto: CharacterDto): CharacterDataModel {
        return CharacterDataModel(dto.id, dto.name, dto.image, Status.fromName(dto.status))
    }
}