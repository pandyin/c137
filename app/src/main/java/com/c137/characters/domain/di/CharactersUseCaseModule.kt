package com.c137.characters.domain.di

import com.c137.characters.domain.GetAllCharactersUseCase
import com.c137.characters.domain.GetAllCharactersUseCaseImpl
import org.koin.dsl.module

val useCaseModule = module {
    factory<GetAllCharactersUseCase> {
        GetAllCharactersUseCaseImpl(get())
    }
}