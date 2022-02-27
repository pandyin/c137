package com.c137.character.data.model.dto

import com.c137.character.data.model.Location

class LocationDtoMapper : DtoMapper<LocationDto, Location> {

    override fun map(dto: LocationDto): Location {
        return Location(dto.id, dto.name, dto.dimension.hashCode())
    }
}