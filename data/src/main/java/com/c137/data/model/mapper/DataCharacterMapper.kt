package com.c137.data.model.mapper

import androidx.paging.PagingData
import androidx.paging.map
import com.c137.data.model.CharacterStatus
import com.c137.data.model.DataCharacter
import com.c137.domain.model.DomainCharacter

class DataCharacterMapper : DataMapper<DataCharacter, DomainCharacter> {

    override fun map(data: DataCharacter): DomainCharacter {
        return DomainCharacter(
            id = data.id,
            name = data.name,
            image = data.image,
            species = data.species,
            isDead = data.status == CharacterStatus.Dead
        )
    }
}

fun DataCharacter.toDomainModel(): DomainCharacter =
    DataCharacterMapper().map(data = this)

fun PagingData<DataCharacter>.toDomainModel(): PagingData<DomainCharacter> =
    map { DataCharacterMapper().map(data = it) }