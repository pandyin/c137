package com.c137.data.model

sealed class CharacterStatus(val name: String) {

    object Dead : CharacterStatus("dead")
    object Alive : CharacterStatus("alive")
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