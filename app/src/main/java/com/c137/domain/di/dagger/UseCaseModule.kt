package com.c137.domain.di.dagger

import com.c137.data.repository.CharacterRepository
import com.c137.data.repository.di.dagger.RepositoryModule
import com.c137.domain.GetCharactersUseCase
import com.c137.domain.GetCharactersUseCaseImpl
import com.c137.presentation.di.dagger.MainActivityScope
import dagger.Module
import dagger.Provides
import dagger.hilt.migration.DisableInstallInCheck

@DisableInstallInCheck
@Module(includes = [RepositoryModule::class])
class UseCaseModule {

    @MainActivityScope
    @Provides
    fun useCase(repository: CharacterRepository): GetCharactersUseCase {
        return GetCharactersUseCaseImpl(repository)
    }
}