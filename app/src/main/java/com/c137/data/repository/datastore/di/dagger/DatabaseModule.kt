package com.c137.data.repository.datastore.di.dagger

import com.c137.C137Database
import com.c137.data.repository.datastore.local.api.CharacterDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun database(): C137Database {
        return C137Database.invoke(null!!)
    }

    @Singleton
    @Provides
    fun characterDao(database: C137Database): CharacterDao {
        return database.characterDao()
    }
}