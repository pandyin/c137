package com.c137.data.model.mapper

import com.c137.data.model.DataCharacter
import com.c137.domain.model.DomainCharacter

class CharacterDataMapper : DataMapper<DataCharacter, DomainCharacter> {

    override fun map(data: DataCharacter): DomainCharacter {
        return DomainCharacter(data.name)
    }
}