package com.c137.di

import android.content.Context
import com.c137.data.C137Database
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
    fun database(@ApplicationContext context: Context) = C137Database.invoke(context)

    @Singleton
    @Provides
    fun characterDao(database: C137Database) = database.characterDao()

    @Singleton
    @Provides
    fun locationDao(database: C137Database) = database.locationDao()
}