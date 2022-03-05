package com.c137.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Character(
    @PrimaryKey val characterId: Int,
    val name: String,
    val image: String,
    val status: Status
) : BaseEntity(characterId)