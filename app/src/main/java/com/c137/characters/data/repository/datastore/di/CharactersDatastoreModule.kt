package com.c137.characters.data.repository.datastore.di

import com.c137.characters.data.repository.datastore.local.CharactersLocalDatastore
import com.c137.characters.data.repository.datastore.local.CharactersLocalDatastoreImpl
import com.c137.characters.data.repository.datastore.remote.CharactersRemoteDatastore
import com.c137.characters.data.repository.datastore.remote.CharactersRemoteDatastoreImpl
import org.koin.dsl.module

val localDatastoreModule = module {
    factory<CharactersLocalDatastore> {
        CharactersLocalDatastoreImpl(get())
    }
}

val remoteDatastoreModule = module {
    factory<CharactersRemoteDatastore> {
        CharactersRemoteDatastoreImpl(get())
    }
}