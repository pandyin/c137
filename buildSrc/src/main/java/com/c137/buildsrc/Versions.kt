package com.c137.buildsrc

object Versions {

    object AppConfig {

        const val compileSdk = 33
        const val minSdk = 21
        const val targetSdk = 33
    }

    object AndroidX {

        const val coreKtx = "1.7.0"
        const val appcompat = "1.5.0"
        const val activityCompose = "1.5.1"
        const val compose = "1.2.1"
        const val lifeCycle = "2.5.1"
        const val room = "2.4.0-alpha03"

        object Paging {
            const val paging = "3.1.1"
            const val pagingCompose = "1.0.0-alpha16"
        }
    }

    object Coroutine {
        const val coroutine = "1.6.4"
    }

    object Google {
        const val material = "1.6.1"
        const val hilt = "2.38.1"
    }

    object Retrofit {
        const val okHttp = "4.9.0"
        const val retrofit = "2.9.0"
    }

    object Test {
        const val junit = "4.13.2"
        const val extJunit = "1.1.3"
        const val espressoCore = "3.4.0"
    }

    object Glide {
        const val glide = "4.13.0"
        const val landscapistGlide = "1.6.1"
    }
}