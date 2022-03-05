package com.c137.di

import com.c137.data.repository.datastore.di.dagger.DatabaseModule
import com.c137.data.repository.datastore.di.dagger.NetworkModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, DatabaseModule::class])
interface C137Component {

}