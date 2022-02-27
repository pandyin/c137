package com.c137.character.data.repository.datastore.di

import com.c137.C137Database
import com.c137.character.data.repository.datastore.local.api.CharacterDao
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseDaoModule = module {
    single<CharacterDao> {
        C137Database.invoke(androidContext())
            .charactersDao()
    }
}