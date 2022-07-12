package com.c137.di

import com.c137.domain.usecase.GetAliveCharactersUseCaseImpl
import com.c137.domain.usecase.GetCharacterByIdUseCaseImpl
import com.c137.domain.usecase.GetCharactersUseCaseImpl
import com.c137.domain.usecase.GetDeadCharactersUseCaseImpl
import com.c137.presentation.api.GetAliveCharactersUseCase
import com.c137.presentation.api.GetCharacterByIdUseCase
import com.c137.presentation.api.GetCharactersUseCase
import com.c137.presentation.api.GetDeadCharactersUseCase
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
    abstract fun bindGetAliveCharactersUseCase(useCase: GetAliveCharactersUseCaseImpl): GetAliveCharactersUseCase

    @ViewModelScoped
    @Binds
    abstract fun bindGetDeadCharactersUseCase(useCase: GetDeadCharactersUseCaseImpl): GetDeadCharactersUseCase
}