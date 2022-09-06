package com.c137.domain.model

import java.util.UUID

data class PresentationCharacter(val name: String = UUID.randomUUID().toString()) :
    BasePresentationModel() {

    operator fun component2(): Boolean = false
}