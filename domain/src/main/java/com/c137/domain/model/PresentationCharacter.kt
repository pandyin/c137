package com.c137.domain.model

data class PresentationCharacter(
    val id: Int,
    val name: String,
    val image: String,
    val isDead: Boolean
) : BasePresentationModel()