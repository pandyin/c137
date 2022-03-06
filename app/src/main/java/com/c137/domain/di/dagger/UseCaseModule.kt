package com.c137.domain.di.dagger

import com.c137.di.ActivityScope
import com.c137.domain.GetCharactersUseCase
import com.c137.domain.GetCharactersUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
abstract class UseCaseModule {

    @ActivityScope
    @Binds
    abstract fun bindUseCase(useCase: GetCharactersUseCaseImpl): GetCharactersUseCase
}