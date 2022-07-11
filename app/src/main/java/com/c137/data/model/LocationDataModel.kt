package com.c137.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LocationDataModel(
    @PrimaryKey val locationId: Int,
    val name: String,
    val dimension: Int,
) : BaseDataModel(locationId)