package com.c137.characters.domain.di

import com.c137.characters.domain.GetCharactersUseCase
import com.c137.characters.domain.GetCharactersUseCaseImpl
import org.koin.dsl.module

val useCaseModule = module {
    factory<GetCharactersUseCase> {
        GetCharactersUseCaseImpl(get())
    }
}