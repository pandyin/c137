package com.c137.di

import com.c137.data.CharacterRemoteDatastore
import com.c137.data.datastore.remote.CharacterRemoteDatastoreImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class RemoteDatastoreModule {

    @ViewModelScoped
    @Binds
    abstract fun bindRemoteDatastore(remoteDatastore: CharacterRemoteDatastoreImpl): CharacterRemoteDatastore
}