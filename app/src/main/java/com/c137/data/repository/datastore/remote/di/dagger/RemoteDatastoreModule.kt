package com.c137.data.repository.datastore.remote.di.dagger

import com.c137.data.repository.datastore.remote.CharacterRemoteDatastore
import com.c137.data.repository.datastore.remote.CharacterRemoteDatastoreImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class RemoteDatastoreModule {

    @ViewModelScoped
    @Binds
    abstract fun bindRemoteDatastore(remoteDatastore: CharacterRemoteDatastoreImpl): CharacterRemoteDatastore
}