package com.c137.characters.data.repository.datastore.di

import com.c137.characters.data.repository.datastore.remote.CharactersRemoteDatastore
import com.c137.characters.data.repository.datastore.remote.CharactersRemoteDatastoreImpl
import org.koin.dsl.module

val datastoreModule = module {
    factory<CharactersRemoteDatastore> {
        CharactersRemoteDatastoreImpl(get())
    }
}