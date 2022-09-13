package com.c137.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "character")
data class DataCharacter(
    @PrimaryKey override val id: Int,
    val name: String,
    val image: String,
    val species: String,
    val origin: Int,
    val location: Int,
    val status: CharacterStatus
) : BaseDataModel(id)