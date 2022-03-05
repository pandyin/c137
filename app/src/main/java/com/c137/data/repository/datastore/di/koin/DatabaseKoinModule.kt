package com.c137.data.repository.datastore.di.koin

import com.c137.C137Database
import com.c137.data.repository.datastore.local.api.CharacterDao
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseDaoKoinModule = module {
    single<CharacterDao> {
        C137Database.invoke(androidContext())
            .characterDao()
    }
}