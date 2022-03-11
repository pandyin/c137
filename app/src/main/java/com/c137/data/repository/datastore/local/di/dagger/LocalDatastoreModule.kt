package com.c137.data.repository.datastore.local.di.dagger

import com.c137.data.repository.datastore.local.CharacterLocalDatastore
import com.c137.data.repository.datastore.local.CharacterLocalDatastoreImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class LocalDatastoreModule {

    @ViewModelScoped
    @Binds
    abstract fun bindLocalDatastore(localDatastore: CharacterLocalDatastoreImpl): CharacterLocalDatastore
}