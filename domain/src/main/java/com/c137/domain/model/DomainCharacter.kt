package com.c137.domain.model

data class DomainCharacter(
    val id: Int,
    val name: String,
    val image: String
) : BaseDomainModel()