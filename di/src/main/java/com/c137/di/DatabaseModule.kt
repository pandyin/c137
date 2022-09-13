package com.c137.di

import android.content.Context
import com.c137.data.C137Database
import com.c137.data.datastore.local.api.CharacterDao
import com.c137.data.datastore.local.api.LocationDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun database(@ApplicationContext context: Context): C137Database {
        return C137Database.invoke(context)
    }

    @Singleton
    @Provides
    fun characterDao(database: C137Database): CharacterDao {
        return database.characterDao()
    }

    @Singleton
    @Provides
    fun locationDao(database: C137Database): LocationDao {
        return database.locationDao()
    }
}