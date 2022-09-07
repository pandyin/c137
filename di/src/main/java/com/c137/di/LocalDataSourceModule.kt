package com.c137.di

import com.c137.data.datastore.local.CharacterLocalDataSourceImpl
import com.c137.data.repository.api.CharacterLocalDataSource
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
    abstract fun bindLocalDataSource(localDataSource: CharacterLocalDataSourceImpl): CharacterLocalDataSource
}