package com.c137.data.repository.di.dagger

import com.c137.data.repository.CharacterRepository
import com.c137.data.repository.CharacterRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @ViewModelScoped
    @Binds
    abstract fun bindRepository(repository: CharacterRepositoryImpl): CharacterRepository
}