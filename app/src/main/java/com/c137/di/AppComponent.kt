package com.c137.di

import com.c137.data.repository.datastore.di.dagger.DatabaseModule
import com.c137.data.repository.datastore.di.dagger.NetworkModule
import com.c137.presentation.di.dagger.MainActivityComponent
import com.c137.presentation.di.dagger.MainActivityModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, DatabaseModule::class, MainActivityModule::class])
interface AppComponent {

    fun mainActivityComponent(): MainActivityComponent.Factory
}