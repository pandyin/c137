package com.c137.di

import com.c137.domain.GetCharacterByIdUseCase
import com.c137.domain.api.CharacterRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class UseCaseModule {

    @ViewModelScoped
    @Provides
    fun provideGetCharacterByIdUseCase(characterRepository: CharacterRepository): GetCharacterByIdUseCase {
        return GetCharacterByIdUseCase(characterRepository)
    }
}