package com.c137.di

import com.c137.domain.usecase.GetCharacterByIdUseCaseImpl
import com.c137.domain.usecase.GetCharactersByStatusUseCaseImpl
import com.c137.domain.usecase.GetCharactersUseCaseImpl
import com.c137.presentation.GetCharacterByIdUseCase
import com.c137.presentation.GetCharactersByStatusUseCase
import com.c137.presentation.GetCharactersUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class UseCaseModule {

    @ViewModelScoped
    @Binds
    abstract fun bindGetCharacterByIdUseCase(useCase: GetCharacterByIdUseCaseImpl): GetCharacterByIdUseCase

    @ViewModelScoped
    @Binds
    abstract fun bindGetCharactersUseCase(useCase: GetCharactersUseCaseImpl): GetCharactersUseCase

    @ViewModelScoped
    @Binds
    abstract fun bindGetCharactersByStatusUseCase(useCase: GetCharactersByStatusUseCaseImpl): GetCharactersByStatusUseCase
}