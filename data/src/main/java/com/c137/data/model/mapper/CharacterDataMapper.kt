package com.c137.data.model.mapper

import com.c137.data.model.DataCharacter
import com.c137.domain.model.DomainCharacter
import com.c137.kmp.episode.Greeting

class CharacterDataMapper : DataMapper<DataCharacter, DomainCharacter> {

    override fun map(data: DataCharacter): DomainCharacter {
//        return DomainCharacter(data.name)
        return DomainCharacter(Greeting().greeting())
    }
}