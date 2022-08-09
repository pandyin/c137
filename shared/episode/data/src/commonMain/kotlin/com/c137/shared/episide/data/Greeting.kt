package com.c137.shared.episide.data

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}