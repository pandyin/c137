package com.c137.data.model.mapper

import androidx.paging.PagingData
import androidx.paging.map
import com.c137.data.model.DataLocation
import com.c137.domain.model.DomainLocation

class DataLocationCharacterMapper : DataMapper<DataLocation, DomainLocation> {

    override fun map(data: DataLocation): DomainLocation {
        return DomainLocation(
            id = data.id,
            name = data.name,
            type = data.type,
            dimension = data.dimension
        )
    }
}

fun DataLocation.toDomainModel(): DomainLocation =
    DataLocationCharacterMapper().map(data = this)

fun PagingData<DataLocation>.toDomainModel(): PagingData<DomainLocation> =
    map { DataLocationCharacterMapper().map(data = it) }