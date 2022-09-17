package com.c137.di

import com.c137.data.datasource.paging.CharacterPagingSourceImpl
import com.c137.data.datasource.paging.EpisodePagingSourceImpl
import com.c137.data.datasource.paging.LocationPagingSourceImpl
import com.c137.data.repository.api.CharacterPagingSource
import com.c137.data.repository.api.EpisodePagingSource
import com.c137.data.repository.api.LocationPagingSource
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
    abstract fun bindCharacterPagingSource(pagingSource: CharacterPagingSourceImpl): CharacterPagingSource

    @ViewModelScoped
    @Binds
    abstract fun bindLocationPagingSource(pagingSource: LocationPagingSourceImpl): LocationPagingSource

    @ViewModelScoped
    @Binds
    abstract fun bindEpisodePagingSource(pagingSource: EpisodePagingSourceImpl): EpisodePagingSource
}