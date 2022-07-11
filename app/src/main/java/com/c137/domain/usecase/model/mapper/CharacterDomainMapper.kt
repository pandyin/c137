package com.c137.domain.usecase.model.mapper

import com.c137.domain.usecase.model.CharacterDomain
import com.c137.presentation.model.CharacterPresentation

class CharacterDomainMapper : DataMapper<CharacterDomain, CharacterPresentation> {

    override fun map(domain: CharacterDomain): CharacterPresentation {
        return CharacterPresentation(domain.name)
    }
}