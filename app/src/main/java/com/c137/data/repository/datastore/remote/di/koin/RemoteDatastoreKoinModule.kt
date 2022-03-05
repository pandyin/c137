package com.c137.data.repository.datastore.remote.di.koin

import com.c137.data.repository.datastore.remote.CharacterRemoteDatastore
import com.c137.data.repository.datastore.remote.CharacterRemoteDatastoreImpl
import org.koin.dsl.module

val characterRemoteDatastoreKoinModule = module {
    factory<CharacterRemoteDatastore> {
        CharacterRemoteDatastoreImpl(get())
    }
}