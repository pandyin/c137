package com.c137.buildsrc

object Dependencies {

    object AndroidX {

        const val coreKtx = "androidx.core:core-ktx:${Versions.AndroidX.coreKtx}"
        const val appcompat = "androidx.appcompat:appcompat:${Versions.AndroidX.appcompat}"
        const val activityCompose = "androidx.activity:activity-compose:${Versions.AndroidX.activityCompose}"

        object Compose {
            const val ui = "androidx.compose.ui:ui:${Versions.AndroidX.compose}"
            const val uiTooling = "androidx.compose.ui:ui-tooling:${Versions.AndroidX.compose}"
            const val uiToolingPreview = "androidx.compose.ui:ui-tooling-preview:${Versions.AndroidX.compose}"
            const val foundation = "androidx.compose.foundation:foundation:${Versions.AndroidX.compose}"
            const val material = "androidx.compose.material:material:${Versions.AndroidX.compose}"
            const val materialIconsCore = "androidx.compose.material:material-icons-core:${Versions.AndroidX.compose}"
            const val materialIconsExtended = "androidx.compose.material:material-icons-extended:${Versions.AndroidX.compose}"
        }

        object Paging {
            const val paging = "androidx.paging:paging-runtime:${Versions.AndroidX.Paging.paging}"
            const val pagingCompose = "androidx.paging:paging-compose:${Versions.AndroidX.Paging.pagingCompose}"
        }

        object LifeCycle {
            const val lifeCycle = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.AndroidX.lifeCycle}"
            const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.AndroidX.lifeCycle}"
            const val viewModelCompose = "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.AndroidX.lifeCycle}"
        }

        object Room {
            const val room = "androidx.room:room-runtime:${Versions.AndroidX.room}"
            const val roomKtx = "androidx.room:room-ktx:${Versions.AndroidX.room}"
            const val roomCompiler = "androidx.room:room-compiler:${Versions.AndroidX.room}"
        }
    }

    object Coroutine {
        const val coroutine = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.Coroutine.coroutine}"
    }

    object Google {
        const val material = "com.google.android.material:material:${Versions.Google.material}"

        object Hilt {
            const val hilt = "com.google.dagger:hilt-android:${Versions.Google.hilt}"
            const val hiltCompiler = "com.google.dagger:hilt-android-compiler:${Versions.Google.hilt}"
        }
    }

    object OkHttp {
        const val okHttp = "com.squareup.okhttp3:okhttp:${Versions.Retrofit.okHttp}"
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.Retrofit.retrofit}"
        const val gsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.Retrofit.retrofit}"
    }

    object Test {
        const val junit = "junit:junit:${Versions.Test.junit}"
        const val extJunit = "androidx.test.ext:junit:${Versions.Test.extJunit}"
        const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.Test.espressoCore}"
    }

    object Glide {
        const val glide = "com.github.bumptech.glide:glide:${Versions.Glide.glide}"
        const val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.Glide.glide}"
        const val landscapistGlide = "com.github.skydoves:landscapist-glide:${Versions.Glide.landscapistGlide}"
    }
}