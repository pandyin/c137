package com.c137.data.repository.di.dagger

import com.c137.data.repository.CharacterRepository
import com.c137.data.repository.CharacterRepositoryImpl
import com.c137.data.repository.datastore.local.CharacterLocalDatastore
import com.c137.data.repository.datastore.local.di.dagger.LocalDatastoreModule
import com.c137.data.repository.datastore.remote.CharacterRemoteDatastore
import com.c137.data.repository.datastore.remote.di.dagger.RemoteDatastoreModule
import com.c137.di.ActivityScope
import dagger.Module
import dagger.Provides
import dagger.hilt.migration.DisableInstallInCheck

@DisableInstallInCheck
@Module(includes = [LocalDatastoreModule::class, RemoteDatastoreModule::class])
class RepositoryModule {

    @ActivityScope
    @Provides
    fun repository(
        localDatastore: CharacterLocalDatastore,
        remoteDatastore: CharacterRemoteDatastore,
    ): CharacterRepository {
        return CharacterRepositoryImpl(localDatastore, remoteDatastore)
    }
}