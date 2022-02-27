package com.c137.character.data.model.dto

data class LocationDto(
    val id: Int,
    val name: String,
    val dimension: String,
    val residents: List<String>
)