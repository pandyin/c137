package com.c137.data.repository.datastore.local.di.dagger

import com.c137.data.repository.datastore.local.CharacterLocalDatastore
import com.c137.data.repository.datastore.local.CharacterLocalDatastoreImpl
import com.c137.di.ActivityScope
import dagger.Binds
import dagger.Module

@Module
abstract class LocalDatastoreModule {

    @ActivityScope
    @Binds
    abstract fun bindLocalDatastore(localDatastore: CharacterLocalDatastoreImpl): CharacterLocalDatastore
}