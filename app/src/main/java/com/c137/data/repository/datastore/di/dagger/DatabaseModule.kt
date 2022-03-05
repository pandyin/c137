package com.c137.data.repository.datastore.di.dagger

import android.content.Context
import com.c137.C137Database
import com.c137.data.repository.datastore.local.api.CharacterDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Singleton
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideCharacterDao(context: Context): CharacterDao {
        return C137Database.invoke(context)
            .characterDao()
    }
}