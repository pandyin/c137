package com.c137.domain.model.mapper

import com.c137.domain.model.DomainCharacter
import com.c137.domain.model.PresentationCharacter

class DomainCharacterMapper : DataMapper<DomainCharacter, PresentationCharacter> {

    override fun map(domain: DomainCharacter): PresentationCharacter {
        return PresentationCharacter(domain.name)
    }
}

fun DomainCharacter.toPresentationModel(): PresentationCharacter =
    DomainCharacterMapper().map(this)

fun List<DomainCharacter>.toPresentationModel(): List<PresentationCharacter> =
    map { DomainCharacterMapper().map(it) }