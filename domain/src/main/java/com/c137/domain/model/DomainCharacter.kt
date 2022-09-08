package com.c137.domain.model

import com.c137.common.model.Location

data class DomainCharacter(
    val id: Int,
    val name: String,
    val image: String,
    val species: String,
    val origin: Location,
    val location: Location,
    val dimensions: List<String>,
    val isDead: Boolean
) : BaseDomainModel()