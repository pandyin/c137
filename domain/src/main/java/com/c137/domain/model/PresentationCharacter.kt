package com.c137.domain.model

data class PresentationCharacter(
    val id: Int,
    val name: String,
    val image: String,
    val species: String,
    val origin: PresentationLocation,
    val location: PresentationLocation,
    val dimensions: List<String>,
    val isDead: Boolean
) : BasePresentationModel()

fun PresentationCharacter.searchKeys(): MutableList<String> {
    val keys = name.lowercase().trim().split(" ").toMutableList()
    keys.add(species.lowercase())
    keys.add(origin.name.lowercase())
    keys.add(location.name.lowercase())
    keys.addAll(dimensions.map { it.lowercase() })
    return keys
}