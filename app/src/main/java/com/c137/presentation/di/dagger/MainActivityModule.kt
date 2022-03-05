package com.c137.presentation.di.dagger

import com.c137.data.repository.CharacterRepository
import com.c137.data.repository.CharacterRepositoryImpl
import com.c137.data.repository.datastore.local.CharacterLocalDatastore
import com.c137.data.repository.datastore.local.CharacterLocalDatastoreImpl
import com.c137.data.repository.datastore.local.api.CharacterDao
import com.c137.data.repository.datastore.remote.CharacterRemoteDatastore
import com.c137.data.repository.datastore.remote.CharacterRemoteDatastoreImpl
import com.c137.data.repository.datastore.remote.api.CharacterService
import com.c137.domain.GetCharactersUseCase
import com.c137.domain.GetCharactersUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.migration.DisableInstallInCheck

@DisableInstallInCheck
@Module
class MainActivityModule {

    @MainActivityScope
    @Provides
    fun localDatastore(dao: CharacterDao): CharacterLocalDatastore {
        return CharacterLocalDatastoreImpl(dao)
    }

    @MainActivityScope
    @Provides
    fun remoteDatastore(service: CharacterService): CharacterRemoteDatastore {
        return CharacterRemoteDatastoreImpl(service)
    }

    @MainActivityScope
    @Provides
    fun repository(
        localDatastore: CharacterLocalDatastore,
        remoteDatastore: CharacterRemoteDatastore,
    ): CharacterRepository {
        return CharacterRepositoryImpl(localDatastore, remoteDatastore)
    }

    @MainActivityScope
    @Provides
    fun useCase(repository: CharacterRepository): GetCharactersUseCase {
        return GetCharactersUseCaseImpl(repository)
    }
}