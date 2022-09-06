package com.c137.data.model.mapper

import com.c137.data.model.DataCharacter
import com.c137.domain.model.DomainCharacter

class DataCharacterMapper : DataMapper<DataCharacter, DomainCharacter> {

    override fun map(data: DataCharacter): DomainCharacter {
        return DomainCharacter(data.name)
    }
}

fun DataCharacter.toDomainModel(): DomainCharacter =
    DataCharacterMapper().map(this)

fun List<DataCharacter>.toDomainModel(): List<DomainCharacter> =
    map { DataCharacterMapper().map(it) }