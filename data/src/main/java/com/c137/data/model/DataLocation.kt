package com.c137.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "location")
data class DataLocation(
    @PrimaryKey override val id: Int,
    val name: String,
    val type: String,
    val dimension: String
) : BaseDataModel(id)