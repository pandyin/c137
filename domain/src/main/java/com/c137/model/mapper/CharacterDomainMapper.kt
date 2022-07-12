package com.c137.model.mapper

import com.c137.model.CharacterDomain
import com.c137.model.CharacterPresentation

class CharacterDomainMapper : DataMapper<CharacterDomain, CharacterPresentation> {

    override fun map(domain: CharacterDomain): CharacterPresentation {
        return CharacterPresentation(domain.name)
    }
}