package com.c137.di

import com.c137.data.datasource.remote.CharacterRemoteDataSourceImpl
import com.c137.data.datasource.remote.EpisodeRemoteDataSourceImpl
import com.c137.data.datasource.remote.LocationRemoteDataSourceImpl
import com.c137.data.repository.api.CharacterRemoteDataSource
import com.c137.data.repository.api.EpisodeRemoteDataSource
import com.c137.data.repository.api.LocationRemoteDataSource
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
    abstract fun bindCharacterRemoteDataSource(remoteDataSource: CharacterRemoteDataSourceImpl): CharacterRemoteDataSource

    @ViewModelScoped
    @Binds
    abstract fun bindLocationRemoteDataSource(remoteDataSource: LocationRemoteDataSourceImpl): LocationRemoteDataSource

    @ViewModelScoped
    @Binds
    abstract fun bindEpisodeRemoteDataSource(remoteDataSource: EpisodeRemoteDataSourceImpl): EpisodeRemoteDataSource
}