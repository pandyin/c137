package com.c137.di

import com.c137.data.repository.datastore.di.dagger.DatabaseModule
import com.c137.data.repository.datastore.di.dagger.NetworkModule
import com.c137.data.repository.datastore.local.api.CharacterDao
import com.c137.data.repository.datastore.remote.api.CharacterService
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, DatabaseModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(context: ContextModule): AppComponent
    }

    fun service(): CharacterService

    fun dao(): CharacterDao
}