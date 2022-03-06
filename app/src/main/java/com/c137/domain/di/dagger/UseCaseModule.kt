package com.c137.domain.di.dagger

import com.c137.data.repository.CharacterRepository
import com.c137.data.repository.di.dagger.RepositoryModule
import com.c137.di.ActivityScope
import com.c137.domain.GetCharactersUseCase
import com.c137.domain.GetCharactersUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.migration.DisableInstallInCheck

@DisableInstallInCheck
@Module(includes = [RepositoryModule::class])
class UseCaseModule {

    @ActivityScope
    @Provides
    fun useCase(repository: CharacterRepository): GetCharactersUseCase {
        return GetCharactersUseCaseImpl(repository)
    }
}