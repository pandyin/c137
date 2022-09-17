package com.c137.di

import com.c137.data.repository.CharacterRepositoryImpl
import com.c137.data.repository.LocationRepositoryImpl
import com.c137.domain.api.CharacterRepository
import com.c137.domain.api.LocationRepository
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
    abstract fun bindCharacterRepository(repository: CharacterRepositoryImpl): CharacterRepository

    @ViewModelScoped
    @Binds
    abstract fun bindLocationRepository(repository: LocationRepositoryImpl): LocationRepository
}