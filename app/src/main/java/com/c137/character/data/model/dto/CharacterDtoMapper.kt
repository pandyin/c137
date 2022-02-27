package com.c137.character.data.model.dto

import com.c137.character.data.model.Character
import com.c137.character.data.model.Status

class CharacterDtoMapper : DtoMapper<CharacterDto, Character> {

    override fun map(dto: CharacterDto): Character {
        return Character(dto.id, dto.name, dto.image, Status.fromName(dto.status))
    }
}