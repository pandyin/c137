package com.c137.data.repository.datastore.remote.di.dagger

import com.c137.data.repository.datastore.remote.CharacterRemoteDatastore
import com.c137.data.repository.datastore.remote.CharacterRemoteDatastoreImpl
import com.c137.data.repository.datastore.remote.api.CharacterService
import com.c137.di.ActivityScope
import dagger.Module
import dagger.Provides
import dagger.hilt.migration.DisableInstallInCheck

@DisableInstallInCheck
@Module
class RemoteDatastoreModule {

    @ActivityScope
    @Provides
    fun remoteDatastore(service: CharacterService): CharacterRemoteDatastore {
        return CharacterRemoteDatastoreImpl(service)
    }
}