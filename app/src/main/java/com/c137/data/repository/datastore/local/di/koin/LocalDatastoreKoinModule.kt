package com.c137.data.repository.datastore.local.di.koin

import com.c137.data.repository.datastore.local.CharacterLocalDatastore
import com.c137.data.repository.datastore.local.CharacterLocalDatastoreImpl
import com.c137.data.repository.datastore.remote.CharacterRemoteDatastore
import com.c137.data.repository.datastore.remote.CharacterRemoteDatastoreImpl
import org.koin.dsl.module

val characterLocalDatastoreKoinModule = module {
    factory<CharacterLocalDatastore> {
        CharacterLocalDatastoreImpl(get())
    }
}