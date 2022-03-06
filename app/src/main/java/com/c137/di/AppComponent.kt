package com.c137.di

import android.content.Context
import com.c137.C137App
import com.c137.data.repository.datastore.di.dagger.DatabaseModule
import com.c137.data.repository.datastore.di.dagger.NetworkModule
import com.c137.data.repository.datastore.local.api.CharacterDao
import com.c137.data.repository.datastore.remote.api.CharacterService
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, DatabaseModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun service(): CharacterService

    fun dao(): CharacterDao

    fun inject(app: C137App)
}