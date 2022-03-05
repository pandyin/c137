package com.c137.data.repository.di.dagger

import com.c137.data.repository.CharacterRepository
import com.c137.data.repository.CharacterRepositoryImpl
import com.c137.data.repository.datastore.local.CharacterLocalDatastore
import com.c137.data.repository.datastore.remote.CharacterRemoteDatastore
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideCharacterRepository(
        localDatastore: CharacterLocalDatastore,
        remoteDatastore: CharacterRemoteDatastore
    ): CharacterRepository {
        return CharacterRepositoryImpl(localDatastore, remoteDatastore)
    }
}