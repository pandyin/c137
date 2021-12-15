package com.c137.characters.data.repository.datastore.di

import com.c137.characters.data.repository.datastore.remote.CharactersRemoteRepository
import com.c137.characters.data.repository.datastore.remote.CharactersRemoteRepositoryImpl
import org.koin.dsl.module

val datastoreModule = module {
    factory<CharactersRemoteRepository> {
        CharactersRemoteRepositoryImpl(get())
    }
}