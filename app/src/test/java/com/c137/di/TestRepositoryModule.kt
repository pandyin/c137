package com.c137.di

import com.c137.characters.data.repository.CharactersRepository
import com.c137.characters.data.repository.CharactersRepositoryImpl
import io.mockk.every
import io.mockk.mockk
import io.reactivex.rxjava3.core.Single
import org.koin.dsl.module

val mockCharacterRepositoryModule = module {
    factory<CharactersRepository> {
        val mockRepository = mockk<CharactersRepositoryImpl>()
        mockRepository
    }
}