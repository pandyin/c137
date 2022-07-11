package com.c137.di

import com.c137.data.CharacterLocalDatastore
import com.c137.data.datastore.local.CharacterLocalDatastoreImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class LocalDatastoreModule {

    @ViewModelScoped
    @Binds
    abstract fun bindLocalDatastore(localDatastore: CharacterLocalDatastoreImpl): CharacterLocalDatastore
}