package com.c137.character.domain.di

import com.c137.character.domain.*
import org.koin.dsl.module

val useCaseModule = module {
    factory<GetCharactersByStatusUseCase> {
        GetCharactersByStatusUseCaseImpl(get())
    }
    factory<GetCharactersUseCase> {
        GetCharactersUseCaseImpl(get())
    }
    factory<GetCharacterByIdUseCase> {
        GetCharacterByIdUseCaseImpl(get())
    }
    factory<GetLocationUseCase> {
        GetLocationUseCaseImpl(get())
    }
}