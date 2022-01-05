package com.c137.characters.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Character(@PrimaryKey val id: Int, val name: String, val image: String)