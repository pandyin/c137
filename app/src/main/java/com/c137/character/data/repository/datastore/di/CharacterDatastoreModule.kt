package com.c137.character.data.repository.datastore.di

import com.c137.character.data.repository.datastore.local.CharacterLocalDatastore
import com.c137.character.data.repository.datastore.local.CharacterLocalDatastoreImpl
import com.c137.character.data.repository.datastore.remote.CharacterRemoteDatastore
import com.c137.character.data.repository.datastore.remote.CharacterRemoteDatastoreImpl
import org.koin.dsl.module

val localDatastoreModule = module {
    factory<CharacterLocalDatastore> {
        CharacterLocalDatastoreImpl(get())
    }
}

val remoteDatastoreModule = module {
    factory<CharacterRemoteDatastore> {
        CharacterRemoteDatastoreImpl(get())
    }
}