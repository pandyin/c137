package com.c137.data.repository.datastore.remote.di.dagger

import com.c137.data.repository.datastore.remote.CharacterRemoteDatastore
import com.c137.data.repository.datastore.remote.CharacterRemoteDatastoreImpl
import com.c137.data.repository.datastore.remote.api.CharacterService
import com.c137.presentation.di.dagger.MainActivityScope
import dagger.Module
import dagger.Provides
import dagger.hilt.migration.DisableInstallInCheck

@DisableInstallInCheck
@Module
class RemoteDatastoreModule {

    @MainActivityScope
    @Provides
    fun remoteDatastore(service: CharacterService): CharacterRemoteDatastore {
        return CharacterRemoteDatastoreImpl(service)
    }
}