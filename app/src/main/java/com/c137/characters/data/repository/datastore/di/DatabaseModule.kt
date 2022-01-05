package com.c137.characters.data.repository.datastore.di

import com.c137.C137Database
import com.c137.characters.data.repository.datastore.local.api.CharactersDao
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseDaoModule = module {
    single<CharactersDao> {
        C137Database.invoke(androidContext())
            .charactersDao()
    }
}