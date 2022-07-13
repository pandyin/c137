package com.c137.di

import com.c137.domain.GetAliveCharactersUseCase
import com.c137.domain.GetCharacterByIdUseCase
import com.c137.domain.GetCharactersUseCase
import com.c137.domain.GetDeadCharactersUseCase
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

    @ViewModelScoped
    @Provides
    fun provideGetCharactersUseCase(characterRepository: CharacterRepository): GetCharactersUseCase {
        return GetCharactersUseCase(characterRepository)
    }

    @ViewModelScoped
    @Provides
    fun provideGetAliveCharactersUseCase(characterRepository: CharacterRepository): GetAliveCharactersUseCase {
        return GetAliveCharactersUseCase(characterRepository)
    }

    @ViewModelScoped
    @Provides
    fun provideGetDeadCharactersUseCase(characterRepository: CharacterRepository): GetDeadCharactersUseCase {
        return GetDeadCharactersUseCase(characterRepository)
    }
}