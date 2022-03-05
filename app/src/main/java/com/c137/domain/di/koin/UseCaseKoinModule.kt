package com.c137.domain.di.koin

import com.c137.domain.*
import org.koin.dsl.module

val useCaseKoinModule = module {
    factory<GetCharactersByStatusUseCase> {
        GetCharactersByStatusUseCaseImpl(get())
    }
    factory<GetCharactersUseCase> {
        GetCharactersUseCaseImpl(get())
    }
    factory<GetCharacterByIdUseCase> {
        GetCharacterByIdUseCaseImpl(get())
    }
}