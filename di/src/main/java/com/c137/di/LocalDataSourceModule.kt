package com.c137.di

import com.c137.data.datasource.local.CharacterLocalDataSourceImpl
import com.c137.data.datasource.local.LocationLocalDataSourceImpl
import com.c137.data.repository.api.CharacterLocalDataSource
import com.c137.data.repository.api.LocationLocalDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class LocalDataSourceModule {

    @ViewModelScoped
    @Binds
    abstract fun bindCharacterLocalDataSource(localDataSource: CharacterLocalDataSourceImpl): CharacterLocalDataSource

    @ViewModelScoped
    @Binds
    abstract fun bindLocationLocalDataSource(localDataSource: LocationLocalDataSourceImpl): LocationLocalDataSource
}