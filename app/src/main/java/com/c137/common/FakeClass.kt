package com.c137.common

import android.view.View
import java.util.*

class FakeClass {

    // Object Expression (anonymous object)
    private val foo = object {
        // Is best for one-time-used.
        // Eager initialization by default.

        private val greetingText = "HELLO_WORLD"

        fun callMe(): String {
            return HELLO_WORLD
        }
    }

    private val callBack = View.OnClickListener { TODO("Not yet implemented") }

    // Object Declaration
    object Hi {
        //Singleton by default.
        //Lazy initialization by default.

        private val greetingText: String by lazy {
            UUID.randomUUID().toString()
        }

        fun callMe(): String = greetingText
    }

    companion object /*NAME*/ {
        // Initialized when the class is loaded.

        const val HELLO_WORLD = "HELLO_WORLD"

        fun callMe(): String {
            return HELLO_WORLD
        }
    }
}