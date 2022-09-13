package com.c137.data.model.mapper.dto

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
            origin = dto.location.id,
            location = dto.location.id,
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