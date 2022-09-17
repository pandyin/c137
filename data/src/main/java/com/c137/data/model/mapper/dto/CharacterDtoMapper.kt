package com.c137.data.model.mapper.dto

import com.c137.data.model.CharacterStatus
import com.c137.data.model.CharacterWithOriginAndLastKnown
import com.c137.data.model.DataCharacter
import com.c137.data.model.DataLocation
import com.c137.data.model.dto.CharacterDto

class CharacterDtoMapper : DtoMapper<CharacterDto, DataCharacter> {

    override fun map(dto: CharacterDto): DataCharacter {
        return DataCharacter(
            id = dto.id,
            name = dto.name,
            image = dto.image,
            species = dto.species,
            originId = dto.origin.id,
            locationId = dto.location.id,
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
            origin = DataLocation(
                id = it.origin.id,
                name = it.origin.name,
                type = "",
                dimension = "",
                residents = emptyList()
            ),
            lastKnown = DataLocation(
                id = it.location.id,
                name = it.location.name,
                type = "",
                dimension = "",
                residents = emptyList()
            )
        )
    }