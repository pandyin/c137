package com.c137.domain.di.dagger

import com.c137.domain.*
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