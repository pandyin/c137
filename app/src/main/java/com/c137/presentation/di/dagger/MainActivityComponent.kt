package com.c137.presentation.di.dagger

import com.c137.di.AppComponent
import com.c137.domain.GetCharactersUseCase
import com.c137.domain.di.dagger.UseCaseModule
import dagger.Component

@MainActivityScope
@Component(modules = [UseCaseModule::class], dependencies = [AppComponent::class])
interface MainActivityComponent {

    fun useCase(): GetCharactersUseCase
}