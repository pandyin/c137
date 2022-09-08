package com.c137.data.model.mapper

import androidx.paging.PagingData
import androidx.paging.map
import com.c137.data.model.CharacterStatus
import com.c137.data.model.DataCharacter
import com.c137.data.model.dto.CharacterDto

class CharacterDtoMapper : DtoMapper<CharacterDto, DataCharacter> {

    override fun map(dto: CharacterDto): DataCharacter {
        return DataCharacter(
            id = dto.id,
            name = dto.name,
            image = dto.image,
            species = dto.species,
            origin = dto.origin.name,
            location = dto.location.name,
            status = CharacterStatus.fromName(dto.status)
        )
    }
}

fun CharacterDto.toDataModel(): DataCharacter =
    CharacterDtoMapper().map(dto = this)

fun List<CharacterDto>.toDataModel(): List<DataCharacter> =
    map { CharacterDtoMapper().map(dto = it) }

fun PagingData<CharacterDto>.toDataModel(): PagingData<DataCharacter> =
    map { CharacterDtoMapper().map(dto = it) }