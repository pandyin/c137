package com.c137.characters.data.model

sealed class Status(val value: String) {

    object Dead : Status("Dead")
    object Alive : Status("Alive")
    object Unknown : Status("unknown")
}