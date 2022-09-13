package com.c137.data.model.mapper.dto

import com.c137.data.model.CharacterStatus
import com.c137.data.model.CharacterWithOriginAndLastKnown
import com.c137.data.model.DataCharacter
import com.c137.data.model.dto.CharacterDto

class CharacterDtoMapper : DtoMapper<CharacterDto, DataCharacter> {

    override fun map(dto: CharacterDto): DataCharacter {
        return DataCharacter(
            id = dto.id,
            name = dto.name,
            image = dto.image,
            species = dto.species,
            originId = dto.location.id ?: 0,
            locationId = dto.location.id ?: 0,
            status = CharacterStatus.fromName(dto.status)
        )
    }
}

fun CharacterDto.toDataModel(): DataCharacter =
    CharacterDtoMapper().map(dto = this)

@Deprecated("to be removed", ReplaceWith(""))
fun List<CharacterDto>.toDataModel(): List<CharacterWithOriginAndLastKnown> =
    map {
        CharacterWithOriginAndLastKnown(
            character = it.toDataModel(),
            origin = it.location.toDataModel(),
            lastKnown = it.location.toDataModel()
        )
    }