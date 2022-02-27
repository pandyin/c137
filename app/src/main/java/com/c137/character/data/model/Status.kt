package com.c137.character.data.model

sealed class Status(val name: String) {

    object Dead : Status("Dead")
    object Alive : Status("Alive")
    object Unknown : Status("unknown")

    companion object {

        fun fromName(name: String): Status {
            return when (name) {
                Dead.name -> Dead
                Alive.name -> Alive
                else -> Unknown
            }
        }
    }
}