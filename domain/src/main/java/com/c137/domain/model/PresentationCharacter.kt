package com.c137.domain.model

data class PresentationCharacter(
    val id: Int,
    val name: String,
    val image: String,
    val species: String,
    val isDead: Boolean
) : BasePresentationModel()