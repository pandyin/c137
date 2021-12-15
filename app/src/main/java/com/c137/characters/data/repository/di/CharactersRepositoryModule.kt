package com.c137.characters.data.repository.di

import com.c137.characters.data.repository.CharactersRepository
import com.c137.characters.data.repository.CharactersRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    factory<CharactersRepository> {
        CharactersRepositoryImpl(get())
    }
}