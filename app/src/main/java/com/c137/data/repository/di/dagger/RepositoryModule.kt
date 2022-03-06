package com.c137.data.repository.di.dagger

import com.c137.data.repository.CharacterRepository
import com.c137.data.repository.CharacterRepositoryImpl
import com.c137.di.ActivityScope
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @ActivityScope
    @Binds
    abstract fun bindRepository(repository: CharacterRepositoryImpl): CharacterRepository
}