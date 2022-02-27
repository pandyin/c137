package com.c137.character.data.model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class LocationWithResidents(
    @Embedded val location: Location,
    @Relation(
        parentColumn = "locationId",
        entityColumn = "characterId",
        associateBy = Junction(ResidentJunction::class)
    ) val residents: List<Character>
)