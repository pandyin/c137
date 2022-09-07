package com.c137.data.model

sealed class CharacterStatus(val name: String) {

    object Dead : CharacterStatus("Dead")
    object Alive : CharacterStatus("Alive")
    object Unknown : CharacterStatus("unknown")

    companion object {

        fun fromName(name: String): CharacterStatus {
            return when (name) {
                Dead.name -> Dead
                Alive.name -> Alive
                else -> Unknown
            }
        }
    }
}