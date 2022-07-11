package com.c137.data.model.mapper

import com.c137.data.model.CharacterData
import com.c137.data.model.Status
import com.c137.data.model.dto.CharacterDto

class CharacterDtoMapper : DtoMapper<CharacterDto, CharacterData> {

    override fun map(dto: CharacterDto): CharacterData {
        return CharacterData(dto.id, dto.name, dto.image, Status.fromName(dto.status))
    }
}