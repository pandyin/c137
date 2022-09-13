package com.c137.domain.model.mapper

import androidx.paging.PagingData
import androidx.paging.map
import com.c137.domain.model.DomainCharacter
import com.c137.domain.model.PresentationCharacter

class DomainCharacterMapper : DataMapper<DomainCharacter, PresentationCharacter> {

    override fun map(domain: DomainCharacter): PresentationCharacter {
        return PresentationCharacter(
            id = domain.id,
            name = domain.name,
            image = domain.image,
            species = domain.species,
            origin = domain.origin.toPresentationModel(),
            location = domain.location.toPresentationModel(),
            dimensions = domain.dimensions,
            isDead = domain.isDead
        )
    }
}

fun DomainCharacter.toPresentationModel(): PresentationCharacter =
    DomainCharacterMapper().map(domain = this)

fun PagingData<DomainCharacter>.toPresentationModel(): PagingData<PresentationCharacter> =
    map { DomainCharacterMapper().map(domain = it) }