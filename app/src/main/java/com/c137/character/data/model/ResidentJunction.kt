package com.c137.character.data.model

import androidx.room.Entity

@Entity(primaryKeys = ["locationId", "characterId"])
data class ResidentJunction(val locationId: Int, val characterId: Int)