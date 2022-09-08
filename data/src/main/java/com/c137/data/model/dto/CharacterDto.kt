package com.c137.data.model.dto

data class CharacterDto(
    val id: Int,
    val name: String,
    val image: String,
    val species: String,
    val origin: LocationDto,
    val location: LocationDto,
    val status: String
)