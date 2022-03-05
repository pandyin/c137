package com.c137.data.repository.datastore.local.di.dagger

import com.c137.data.repository.datastore.local.CharacterLocalDatastore
import com.c137.data.repository.datastore.local.CharacterLocalDatastoreImpl
import com.c137.data.repository.datastore.local.api.CharacterDao
import com.c137.presentation.di.dagger.MainActivityScope
import dagger.Module
import dagger.Provides
import dagger.hilt.migration.DisableInstallInCheck

@DisableInstallInCheck
@Module
class LocalDatastoreModule {

    @MainActivityScope
    @Provides
    fun localDatastore(dao: CharacterDao): CharacterLocalDatastore {
        return CharacterLocalDatastoreImpl(dao)
    }
}