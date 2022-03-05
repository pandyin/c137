package com.c137.data.repository.datastore.di.dagger

import com.c137.data.repository.datastore.local.CharacterLocalDatastore
import com.c137.data.repository.datastore.local.CharacterLocalDatastoreImpl
import com.c137.data.repository.datastore.local.api.CharacterDao
import com.c137.data.repository.datastore.remote.CharacterRemoteDatastore
import com.c137.data.repository.datastore.remote.CharacterRemoteDatastoreImpl
import com.c137.data.repository.datastore.remote.api.CharacterService
import dagger.Module
import dagger.Provides

@Module
class DatastoreModule {

    @Provides
    fun provideCharacterLocalDatastore(dao: CharacterDao): CharacterLocalDatastore {
        return CharacterLocalDatastoreImpl(dao)
    }

    @Provides
    fun provideCharacterRemoteDatastore(service: CharacterService): CharacterRemoteDatastore {
        return CharacterRemoteDatastoreImpl(service)
    }
}