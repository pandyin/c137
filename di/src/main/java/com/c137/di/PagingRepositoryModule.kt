package com.c137.di

import com.c137.data.repository.CharacterPagingRepositoryImpl
import com.c137.data.repository.EpisodePagingRepositoryImpl
import com.c137.data.repository.LocationPagingRepositoryImpl
import com.c137.domain.api.CharacterPagingRepository
import com.c137.domain.api.EpisodePagingRepository
import com.c137.domain.api.LocationPagingRepository
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
    abstract fun bindCharacterPagingRepository(repository: CharacterPagingRepositoryImpl): CharacterPagingRepository

    @ViewModelScoped
    @Binds
    abstract fun bindLocationPagingRepository(repository: LocationPagingRepositoryImpl): LocationPagingRepository

    @ViewModelScoped
    @Binds
    abstract fun bindPagingEpisodeRepository(repository: EpisodePagingRepositoryImpl): EpisodePagingRepository
}