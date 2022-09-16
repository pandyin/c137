package com.c137.data.model.mapper.dto

import android.net.Uri
import com.c137.data.model.DataLocation
import com.c137.data.model.dto.LocationDto

class LocationDtoMapper : DtoMapper<LocationDto, DataLocation> {

    override fun map(dto: LocationDto): DataLocation {
        return DataLocation(
            id = dto.id,
            name = dto.name,
            type = dto.type,
            dimension = dto.dimension,
            residents = dto.residents.mapNotNull { Uri.parse(it).lastPathSegment?.toIntOrNull() }
        )
    }
}

fun LocationDto.toDataModel(): DataLocation =
    LocationDtoMapper().map(dto = this)

@Deprecated("to be removed", ReplaceWith(""))
fun List<LocationDto>.toDataModel(): List<DataLocation> =
    map { LocationDtoMapper().map(dto = it) }