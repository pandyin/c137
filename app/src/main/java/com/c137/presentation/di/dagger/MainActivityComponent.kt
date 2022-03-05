package com.c137.presentation.di.dagger

import com.c137.di.AppComponent
import com.c137.domain.GetCharactersUseCase
import dagger.Component

@MainActivityScope
@Component(modules = [MainActivityModule::class], dependencies = [AppComponent::class])
interface MainActivityComponent {

    fun useCase(): GetCharactersUseCase
}