package com.c137.domain.model

data class PresentationCharacter(
    val id: Int,
    val name: String,
    val image: String
) : BasePresentationModel() {

    operator fun component4(): Boolean = false
}