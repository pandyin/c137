package com.c137.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "character")
data class CharacterData(
    @PrimaryKey val characterId: Int,
    val name: String,
    val image: String,
    val status: CharacterStatus
) : BaseDataModel(characterId)