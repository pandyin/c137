package com.c137.domain.model.mapper

import com.c137.domain.model.CharacterDomain
import com.c137.domain.model.CharacterPresentation

class CharacterDomainMapper : DataMapper<CharacterDomain, CharacterPresentation> {

    override fun map(domain: CharacterDomain): CharacterPresentation {
        return CharacterPresentation(domain.name)
    }
}