package com.c137.presentation.di.dagger

import com.c137.di.ActivityScope
import com.c137.domain.di.dagger.UseCaseModule
import com.c137.presentation.MainActivity
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [UseCaseModule::class])
interface MainActivityComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): MainActivityComponent
    }

    fun inject(mainActivity: MainActivity)
}