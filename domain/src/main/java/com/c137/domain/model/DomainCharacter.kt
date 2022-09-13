package com.c137.domain.model

data class DomainCharacter(
    val id: Int,
    val name: String,
    val image: String,
    val species: String,
    val dimensions: List<String>,
    val isDead: Boolean
) : BaseDomainModel() {

    lateinit var origin: DomainLocation
    lateinit var lastKnown: DomainLocation
}