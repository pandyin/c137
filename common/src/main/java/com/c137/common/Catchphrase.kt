package com.c137.common

import kotlin.random.Random
import kotlin.random.nextInt

object Catchphrase {

    fun burp(): String {
        return when (Random.nextInt(IntRange(1, 10))) {
            1 -> "Get Schwifty!"
            2 -> "WUBBA LUBBA DUB-DUB!"
            3 -> "Tiny Rick!"
            4 -> "WHAT'S UP MY GLIP GLOPS!"
            5 -> "GET THAT PARKOUR!"
            6 -> "In Bird Culture, This Is Considered A D*ck Move."
            7 -> "OOH WEE!"
            8 -> "Anything From The Cromulons"
            9 -> "AND THAT'S THE WAYYYYYY THE NEWS GOES"
            else -> "Squanch"
        }
    }
}