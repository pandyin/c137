package com.c137.data.model.mapper

import androidx.paging.PagingData
import androidx.paging.map
import com.c137.data.model.CharacterStatus
import com.c137.data.model.DataCharacter
import com.c137.data.model.dto.CharacterDto

class CharacterDtoMapper : DtoMapper<CharacterDto, DataCharacter> {

    override fun map(dto: CharacterDto): DataCharacter {
        return DataCharacter(dto.id, dto.name, dto.image, CharacterStatus.fromName(dto.status))
    }
}

fun CharacterDto.toDataModel(): DataCharacter =
    CharacterDtoMapper().map(this)

fun List<CharacterDto>.toDataModel(): List<DataCharacter> =
    map { CharacterDtoMapper().map(it) }

fun PagingData<CharacterDto>.toDataModel(): PagingData<DataCharacter> =
    map { CharacterDtoMapper().map(it) }