package com.c137.data.model.mapper

import com.c137.data.model.CharacterData
import com.c137.model.CharacterDomain

class CharacterDataMapper : DataMapper<CharacterData, CharacterDomain> {

    override fun map(data: CharacterData): CharacterDomain {
        return CharacterDomain(data.name)
    }
}