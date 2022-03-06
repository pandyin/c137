package com.c137.presentation.di.dagger

import com.c137.di.ActivityScope
import com.c137.di.AppComponent
import com.c137.domain.di.dagger.UseCaseModule
import com.c137.presentation.MainActivity
import dagger.Component

@ActivityScope
@Component(modules = [UseCaseModule::class], dependencies = [AppComponent::class])
interface MainActivityComponent {

    @Component.Factory
    interface Factory {
        fun create(appComponent: AppComponent): MainActivityComponent
    }

    fun inject(mainActivity: MainActivity)
}