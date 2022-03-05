package com.c137.data.repository.di.koin

import com.c137.data.repository.CharacterRepository
import com.c137.data.repository.CharacterRepositoryImpl
import org.koin.dsl.module

val characterRepositoryKoinModule = module {
    factory<CharacterRepository> {
        CharacterRepositoryImpl(get(), get())
    }
}