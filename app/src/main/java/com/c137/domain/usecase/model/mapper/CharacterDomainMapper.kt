package com.c137.domain.usecase.model.mapper

class CharacterDomainMapper : DataMapper<com.c137.domain.usecase.model.CharacterDomain, Character> {

    override fun map(domain: com.c137.domain.usecase.model.CharacterDomain): Character {
        return Character(domain.name)
    }
}