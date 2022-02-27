package com.c137.character.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Location(@PrimaryKey val locationId: Int, val name: String, val dimension: Int) :
    BaseEntity(locationId)