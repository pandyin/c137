package com.c137.di

import com.c137.data.datastore.paging.CharacterPagingSourceImpl
import com.c137.data.repository.api.CharacterPagingSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class PagingSourceModule {

    @ViewModelScoped
    @Binds
    abstract fun bindPagingSource(pagingSource: CharacterPagingSourceImpl): CharacterPagingSource
}