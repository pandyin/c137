package com.c137.data.repository.datastore.local.di.dagger

import com.c137.data.repository.datastore.local.CharacterLocalDatastore
import com.c137.data.repository.datastore.local.CharacterLocalDatastoreImpl
import com.c137.data.repository.datastore.local.api.CharacterDao
import com.c137.di.ActivityScope
import dagger.Module
import dagger.Provides
import dagger.hilt.migration.DisableInstallInCheck

@DisableInstallInCheck
@Module
class LocalDatastoreModule {

    @ActivityScope
    @Provides
    fun localDatastore(dao: CharacterDao): CharacterLocalDatastore {
        return CharacterLocalDatastoreImpl(dao)
    }
}