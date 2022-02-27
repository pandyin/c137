package com.c137.character.data.repository.di

import com.c137.character.data.repository.CharacterRepository
import com.c137.character.data.repository.CharacterRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    factory<CharacterRepository> {
        CharacterRepositoryImpl(get(), get())
    }
}