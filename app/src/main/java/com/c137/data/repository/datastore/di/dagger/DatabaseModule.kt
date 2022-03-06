package com.c137.data.repository.datastore.di.dagger

import android.content.Context
import com.c137.C137Database
import com.c137.data.repository.datastore.local.api.CharacterDao
import dagger.Module
import dagger.Provides
import dagger.hilt.migration.DisableInstallInCheck
import javax.inject.Singleton

@DisableInstallInCheck
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun database(context: Context): C137Database {
        return C137Database.invoke(context)
    }

    @Singleton
    @Provides
    fun characterDao(database: C137Database): CharacterDao {
        return database.characterDao()
    }
}