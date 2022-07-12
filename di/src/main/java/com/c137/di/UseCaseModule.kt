package com.c137.di

import com.c137.di.usecase.GetAliveCharactersUseCaseImpl
import com.c137.di.usecase.GetCharacterByIdUseCaseImpl
import com.c137.di.usecase.GetCharactersUseCaseImpl
import com.c137.di.usecase.GetDeadCharactersUseCaseImpl
import com.c137.feature.search.api.GetAliveCharactersUseCase
import com.c137.feature.search.api.GetCharacterByIdUseCase
import com.c137.feature.search.api.GetCharactersUseCase
import com.c137.feature.search.api.GetDeadCharactersUseCase
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