package com.c137.data.model

import androidx.room.Embedded
import androidx.room.Relation

data class CharacterWithOriginAndLastKnown(
    @Embedded val character: DataCharacter,
    @Relation(
        parentColumn = "originId",
        entityColumn = "id"
    )
    val origin: DataLocation,
    @Relation(
        parentColumn = "locationId",
        entityColumn = "id"
    )
    val lastKnown: DataLocation
)