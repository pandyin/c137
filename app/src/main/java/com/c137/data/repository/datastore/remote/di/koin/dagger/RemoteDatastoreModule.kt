package com.c137.data.repository.datastore.remote.di.koin.dagger

import com.c137.data.repository.datastore.remote.CharacterRemoteDatastore
import com.c137.data.repository.datastore.remote.CharacterRemoteDatastoreImpl
import com.c137.di.ActivityScope
import dagger.Binds
import dagger.Module

@Module
abstract class RemoteDatastoreModule {

    @ActivityScope
    @Binds
    abstract fun bindRemoteDatastore(remoteDatastore: CharacterRemoteDatastoreImpl): CharacterRemoteDatastore
}