package com.c137.data.model.dto

import android.net.Uri

data class CharacterDto(
    val id: Int,
    val name: String,
    val image: String,
    val species: String,
    val origin: CharacterLocation,
    val location: CharacterLocation,
    val status: String
)

data class CharacterLocation(val name: String, val url: String) {

    val id: Int?
        get() = Uri.parse(url).lastPathSegment?.toIntOrNull()
}