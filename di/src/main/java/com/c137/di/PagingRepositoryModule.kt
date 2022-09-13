package com.c137.di

import com.c137.data.repository.CharacterPagingRepositoryImpl
import com.c137.domain.api.CharacterPagingRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class PagingRepositoryModule {

    @ViewModelScoped
    @Binds
    abstract fun bindPagingRepository(repository: CharacterPagingRepositoryImpl): CharacterPagingRepository
}