package com.c137.di

import com.c137.characters.domain.GetCharactersUseCase
import com.c137.characters.domain.GetCharactersUseCaseImpl
import io.mockk.every
import io.mockk.mockk
import io.reactivex.rxjava3.core.Single
import org.koin.dsl.module

val mockGetCharactersUseCaseModule = module {
    factory<GetCharactersUseCase> {
        val mockGetCharactersUseCase = mockk<GetCharactersUseCaseImpl>()
        mockGetCharactersUseCase
    }
}