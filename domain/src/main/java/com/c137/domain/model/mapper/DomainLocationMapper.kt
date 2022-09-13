package com.c137.domain.model.mapper

import androidx.paging.PagingData
import androidx.paging.map
import com.c137.domain.model.DomainLocation
import com.c137.domain.model.PresentationLocation

class DomainLocationMapper : DataMapper<DomainLocation, PresentationLocation> {

    override fun map(domain: DomainLocation): PresentationLocation {
        return PresentationLocation(
            id = domain.id,
            name = domain.name,
            type = domain.type,
            dimension = domain.dimension
        )
    }
}

fun DomainLocation.toPresentationModel(): PresentationLocation =
    DomainLocationMapper().map(domain = this)

fun PagingData<DomainLocation>.toPresentationModel(): PagingData<PresentationLocation> =
    map { DomainLocationMapper().map(domain = it) }