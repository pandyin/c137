package com.c137.di

import com.c137.data.repository.CharacterRepositoryImpl
import com.c137.data.repository.PagingCharacterRepositoryImpl
import com.c137.domain.api.CharacterRepository
import com.c137.domain.api.PagingCharacterRepository
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

    @ViewModelScoped
    @Binds
    abstract fun bindPagingRepository(repository: PagingCharacterRepositoryImpl): PagingCharacterRepository
}