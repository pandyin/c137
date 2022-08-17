package com.c137.domain.model

data class PresentationCharacter(val name: String) : BasePresentationModel() {

    operator fun component2(): Boolean = false
}