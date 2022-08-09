package com.c137.shared.episode.domain

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}