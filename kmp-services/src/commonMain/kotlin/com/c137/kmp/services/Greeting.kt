package com.c137.kmp.services

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}