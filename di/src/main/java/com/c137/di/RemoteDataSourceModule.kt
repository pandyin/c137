package com.c137.di

import com.c137.data.datastore.remote.CharacterRemoteDataSourceImpl
import com.c137.data.repository.api.CharacterRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class RemoteDataSourceModule {

    @ViewModelScoped
    @Binds
    abstract fun bindRemoteDataSource(remoteDataSource: CharacterRemoteDataSourceImpl): CharacterRemoteDataSource
}