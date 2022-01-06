package com.c137.characters.domain.di

import com.c137.characters.domain.GetCharactersByStatusUseCase
import com.c137.characters.domain.GetCharactersByStatusUseCaseImpl
import com.c137.characters.domain.GetCharactersUseCase
import com.c137.characters.domain.GetCharactersUseCaseImpl
import org.koin.dsl.module

val useCaseModule = module {
    factory<GetCharactersByStatusUseCase> {
        GetCharactersByStatusUseCaseImpl(get())
    }
    factory<GetCharactersUseCase> {
        GetCharactersUseCaseImpl(get())
    }
}